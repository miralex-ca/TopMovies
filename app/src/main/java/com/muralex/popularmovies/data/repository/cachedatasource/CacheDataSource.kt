package com.muralex.popularmovies.data.repository.cachedatasource

import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.Resource

interface CacheDataSource {
    fun getArticlesFromCache() : Resource<List<Article>>
    fun saveArticlesToCache(articles: Resource<List<Article>>)
}
