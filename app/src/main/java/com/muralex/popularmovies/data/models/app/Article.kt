package com.muralex.popularmovies.data.models.app

import java.io.Serializable

data class Article (
    val id : Int,
    val title: String,
    val text: String,
    val thumbNail: String,
    val image: String,
    val release: String,
    val releaseMonth: String,
    val vote: Double
) : Serializable
