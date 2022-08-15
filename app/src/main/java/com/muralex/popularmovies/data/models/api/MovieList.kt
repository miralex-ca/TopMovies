package com.muralex.popularmovies.data.models.api

import com.muralex.popularmovies.data.models.api.ApiData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val movies: List<ApiData>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)