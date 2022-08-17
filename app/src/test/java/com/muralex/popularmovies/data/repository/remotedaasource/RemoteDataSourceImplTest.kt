package com.muralex.popularmovies.data.repository.remotedaasource

import com.google.common.truth.Truth.assertThat
import com.muralex.popularmovies.data.api.MoviesApiService
import com.muralex.popularmovies.data.mappers.ApiDataToArticleMapper
import com.muralex.popularmovies.data.models.api.MovieList
import com.muralex.topmovies.common.utils.NetworkHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest  {

    private lateinit var SUT: RemoteDataSourceImpl
    private val api: MoviesApiService = mockk()
    private val mapper: ApiDataToArticleMapper = mockk()
    private val networkHelper: NetworkHelper = mockk()

    @Test
    fun getNews_invoke_networkHelper_isNetworkConnected() = runTest {
        mockSuccess()
        SUT.getNewsDataFromApi()
        coVerify { networkHelper.isNetworkConnected() }
    }

    @Test
    fun getNews_invoke_apiService_getHeadlines() = runTest {
        mockSuccess()
        SUT.getNewsDataFromApi()
        coVerify { api.getPopularMovies() }
    }

    @Test
    fun getNews_successResponse_mapperMapFromEntityList() = runTest {
        mockSuccess()
        SUT.getNewsDataFromApi()
        coVerify { mapper.mapFromEntityList(any()) }
    }

    @Test
    fun getNews_successResponse_getResourceSuccess() = runTest {
        mockSuccess()
        val result = SUT.getNewsDataFromApi()
        assertThat(result.isSuccess()).isTrue()
    }

    @Test
    fun getNews_responseError_isError() = runTest {
        mockError()
        val result = SUT.getNewsDataFromApi()
        assertThat(result.isError()).isTrue()
    }

    @Test
    fun getNews_responseException_isError() = runTest {
        mockException()
        val result = SUT.getNewsDataFromApi()
        assertThat(result.isError()).isTrue()
    }

    @Test
    fun getNews_noConnection_isError() = runTest {
        mockNoConnection()
        val result = SUT.getNewsDataFromApi()
        assertThat(result.isError()).isTrue()
    }

    private fun mockNoConnection() = runTest {
        coEvery { networkHelper.isNetworkConnected() } returns false
        SUT = RemoteDataSourceImpl(api, mapper, networkHelper)
    }

    private fun mockSettingsAndNetworkHelpers() = runTest {
        coEvery { networkHelper.isNetworkConnected() } returns true
    }

    private fun mockSuccess() = runTest {
        mockSettingsAndNetworkHelpers()
        coEvery { api.getPopularMovies() } returns testResponse
        coEvery { mapper.mapFromEntityList(any()) } returns emptyList()
        SUT = RemoteDataSourceImpl(api, mapper, networkHelper)
    }

    private fun mockException() = runTest {
        mockSettingsAndNetworkHelpers()
        coEvery { api.getPopularMovies() } throws Exception()
        SUT = RemoteDataSourceImpl(api, mapper, networkHelper)
    }

    private fun mockError() = runTest {
        mockSettingsAndNetworkHelpers()
        coEvery { api.getPopularMovies() } returns testErrorResponse
        SUT = RemoteDataSourceImpl(api, mapper, networkHelper)
    }

    companion object {
        private val testApiResponse = MovieList(emptyList())
        private val testResponse: Response<MovieList> = Response.success(testApiResponse)
        private val testErrorResponse: Response<MovieList> = mockk()
    }

}
