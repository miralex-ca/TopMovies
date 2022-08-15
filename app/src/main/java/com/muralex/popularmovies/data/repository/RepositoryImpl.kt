package com.muralex.popularmovies.data.repository

import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.data.repository.cachedatasource.CacheDataSource
import com.muralex.popularmovies.domain.MovieRepository
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.data.repository.remotedaasource.RemoteDataSource


class RepositoryImpl(
    private val cacheDataSource: CacheDataSource,
    private val remoteDataSource: RemoteDataSource,
) : MovieRepository {

    override suspend fun getMovies(): Resource<List<Article>> {
        return getArticlesFromCache()
    }

    override suspend fun updateMovies(): Resource<List<Article>> {
        var update = getNewsDataFromApi()
        cacheDataSource.saveArticlesToCache(update)
        if (update.isError()) {
            update = Resource.error(update.message.toString(), update.data).apply {
                setInfo(update.getInfo())
            }
        }
        return update
    }

    private suspend fun getNewsDataFromApi() = remoteDataSource.getNewsDataFromApi()

    private suspend fun getArticlesFromCache(): Resource<List<Article>> {
        var articles = cacheDataSource.getArticlesFromCache()
        if (articles.data.isNullOrEmpty()) {
            articles = getNewsDataFromApi()
            cacheDataSource.saveArticlesToCache(articles)
        }
        return articles
    }


}