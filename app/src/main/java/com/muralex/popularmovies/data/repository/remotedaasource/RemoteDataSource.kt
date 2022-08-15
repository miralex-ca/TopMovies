package com.muralex.popularmovies.data.repository.remotedaasource

import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.Resource

interface RemoteDataSource {
    suspend fun getNewsDataFromApi() : Resource<List<Article>>
}