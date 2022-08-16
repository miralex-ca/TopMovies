package com.muralex.popularmovies.data.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList(
    @Json(name = "results")
    val movies: List<ApiData>
)