package com.muralex.popularmovies.data.repository

import com.google.common.truth.Truth.assertThat
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.common.data.Status
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.data.repository.cachedatasource.CacheDataSource
import com.muralex.popularmovies.data.repository.remotedaasource.RemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    private lateinit var SUT: RepositoryImpl
    private val cacheDataSource: CacheDataSource = mockk()
    private val remoteDataSource: RemoteDataSource = mockk()
    private val testData: Resource<List<Article>> = mockk()
    private val testArticle: Article = mockk()
    private val testArticleList = listOf(testArticle)

    @Test
    fun getNewsArticles_emptyData_invokeAllDataSources() = runTest {
        mockEmptyListSuccess()
        SUT.getMovies()
        coVerify { cacheDataSource.getArticlesFromCache() }
        coVerify { remoteDataSource.getNewsDataFromApi() }

    }

    @Test
    fun getNewsArticles_notEmptyCacheData_invokeOnlyCache() = runTest {
        mockNotEmptyCache()
        SUT.getMovies()
        coVerify { cacheDataSource.getArticlesFromCache() }
    }

    @Test
    fun getNewsArticles_emptySuccessData_getSuccess() = runTest {
        mockEmptyListSuccess()
        val result = SUT.getMovies()
        assertThat(result.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun getNewsArticles_emptyError_getError() = runTest {
        mockEmptyListError()
        val result = SUT.getMovies()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun updateArticles_invokeRemoteDataAndSaveCache() = runTest {
        mockEmptyListSuccess()
        SUT.updateMovies()
        coVerify { remoteDataSource.getNewsDataFromApi() }
        coVerify { cacheDataSource.saveArticlesToCache(any()) }
    }

    private fun mockEmptyList() {
        coEvery { cacheDataSource.saveArticlesToCache(any()) } returns Unit
        coEvery { cacheDataSource.saveArticlesToCache(testData) } returns Unit
        coEvery { testData.data } returns emptyList()
        coEvery { remoteDataSource.getNewsDataFromApi() } returns testData
        coEvery { cacheDataSource.getArticlesFromCache() } returns testData
    }

    private fun mockEmptyListSuccess() {
        mockEmptyList()
        coEvery { testData.isError() } returns false
        coEvery { testData.status } returns Status.SUCCESS
        SUT = RepositoryImpl(cacheDataSource, remoteDataSource)
    }

    private fun mockEmptyListError() {
        mockEmptyList()
        coEvery { testData.status } returns Status.ERROR
        SUT = RepositoryImpl(cacheDataSource, remoteDataSource)
    }

    private fun mockNotEmptyCache() {
        mockEmptyList()
        coEvery { testData.data } returns testArticleList
        coEvery { testData.status } returns Status.SUCCESS
        SUT = RepositoryImpl(cacheDataSource, remoteDataSource)
    }

}