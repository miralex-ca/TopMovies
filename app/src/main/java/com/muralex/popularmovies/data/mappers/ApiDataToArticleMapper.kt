package com.muralex.popularmovies.data.mappers

import android.os.Build
import android.util.Log
import com.muralex.topmovies.common.utils.Constants.IMAGE_DIR
import com.muralex.popularmovies.data.models.api.ApiData
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.EntityMapper
import com.muralex.topmovies.common.utils.Constants.BIG_IMAGE_DIR
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ApiDataToArticleMapper : EntityMapper<ApiData, Article> {

    override fun mapFromEntity(data: ApiData): Article {

        val published = processDate(data.releaseDate)
        val publishedMonth = processDate(data.releaseDate)

        return Article(
            id = data.id,
            title = data.title ?: "",
            text = data.overview ?: "",
            thumbNail = IMAGE_DIR + data.posterPath ?: "",
            image= BIG_IMAGE_DIR + data.posterPath ?: "",
            release = published.formatToDate(),
            releaseMonth = published.formatToShortDate(),
            vote = data.vote ?: 0.0
        )
    }

    fun mapFromEntityList(entitiesList: List<ApiData>?): List<Article> {
        return entitiesList?.map { mapFromEntity(it) } ?: emptyList()
    }

    private fun processDate(date: String?) : Long {
        var published  =  0L

        try {
            published = getTimeFromData(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return published
    }

    private fun getTimeFromData(date: String?) : Long {
        var published  =  0L
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        date?.let {
            val parse : Date? = simpleDateFormat.parse(it)
            published = parse?.time ?: 0L
        }
        return published
    }

    private fun Long.formatToShortDate(locale: Locale = Locale.US): String {
        var time = ""
        try {
            val formatter =  SimpleDateFormat("MMM yyyy", locale)
            time = formatter.format(this)

        } catch (e: Exception) {
            Timber.d("Error: ${e.message}")
        }
        return time
    }

    private fun Long.formatToDate(locale: Locale = Locale.US): String {
        var time = ""
        try {
            val formatter =  SimpleDateFormat("MMMM dd, yyyy", locale)
            time = formatter.format(this)

        } catch (e: Exception) {
            Timber.d("Error: ${e.message}")
        }
        return time
    }


}