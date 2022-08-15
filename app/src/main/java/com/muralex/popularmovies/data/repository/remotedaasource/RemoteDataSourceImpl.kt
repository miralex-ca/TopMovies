package com.muralex.popularmovies.data.repository.remotedaasource

import com.muralex.topmovies.common.utils.Constants.DATA_FETCH_ERROR
import com.muralex.topmovies.common.utils.NetworkHelper
import com.muralex.popularmovies.data.api.MoviesApiService
import com.muralex.popularmovies.data.mappers.ApiDataToArticleMapper
import com.muralex.popularmovies.data.models.api.MovieList
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.common.data.ResourceInfo
import retrofit2.Response
import timber.log.Timber

class RemoteDataSourceImpl(
    private val apiService: MoviesApiService,
    private val mapper: ApiDataToArticleMapper,
    private val networkHelper: NetworkHelper
) : RemoteDataSource {

    override suspend fun getNewsDataFromApi() = getNewsData()

    private suspend fun getNewsData(): Resource<List<Article>> {

        if (!networkHelper.isNetworkConnected())
            return handleConnectionError()

        return try {
            val response: Response<MovieList> = apiService.getPopularMovies()
            if (response.isSuccessful) {
                mapResponseToData(response)
            } else {
                handleResponseError()
            }
        } catch (e: Exception) {
            Timber.d(e, "Unable to get API response")
            handleRequestException(e)
        }
    }

    private fun mapResponseToData(response: Response<MovieList>): Resource<List<Article>> {
        return response.body()?.let {
            val articles = mapper.mapFromEntityList(it.movies)
            Resource.success(articles)
        } ?: Resource.success(emptyList())
    }

    private fun handleConnectionError(): Resource<Nothing> {
        return Resource.error(DATA_FETCH_ERROR, null).apply {
            setInfo(ResourceInfo.ConnectionError)
        }
    }

    private fun handleResponseError(): Resource<Nothing> {
        return Resource.error(DATA_FETCH_ERROR, null).apply {
            setInfo(ResourceInfo.RequestError)
        }
    }

    private fun handleRequestException(e: Exception): Resource<Nothing> {
        return Resource.error(DATA_FETCH_ERROR, null).apply {
            setInfo(ResourceInfo.ErrorException(e))
        }
    }
}