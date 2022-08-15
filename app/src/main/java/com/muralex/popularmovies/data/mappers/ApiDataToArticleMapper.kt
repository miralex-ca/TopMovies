package com.muralex.popularmovies.data.mappers

import com.muralex.topmovies.common.utils.Constants.IMAGE_DIR
import com.muralex.popularmovies.data.models.api.ApiData
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.common.data.EntityMapper

class ApiDataToArticleMapper : EntityMapper<ApiData, Article> {

    override fun mapFromEntity(data: ApiData): Article {
        return Article(
            id = data.id,
            title = data.title ?: "",
            text = data.overview ?: "",
            image= IMAGE_DIR + data.posterPath ?: "",
            release = data.releaseDate ?: "",
            vote = data.vote ?: 0.0
        )
    }

    fun mapFromEntityList(entitiesList: List<ApiData>?): List<Article> {
        return entitiesList?.map { mapFromEntity(it) } ?: emptyList()
    }
}