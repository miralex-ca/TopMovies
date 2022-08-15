package com.muralex.popularmovies.domain

import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.Resource


interface MovieRepository {
    suspend fun getMovies() : Resource<List<Article>>
    suspend fun updateMovies(): Resource<List<Article>>
}