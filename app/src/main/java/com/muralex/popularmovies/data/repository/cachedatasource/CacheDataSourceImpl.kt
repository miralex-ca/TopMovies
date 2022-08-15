package com.muralex.popularmovies.data.repository.cachedatasource

import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.common.data.Status
import com.muralex.popularmovies.data.repository.cachedatasource.CacheDataSource

class CacheDataSourceImpl: CacheDataSource {

    private var articles = Resource(Status.LOADING, emptyList<Article>(), "")

    override fun getArticlesFromCache() = articles

    override fun saveArticlesToCache(articles: Resource<List<Article>>) {
        this.articles = articles
    }
}