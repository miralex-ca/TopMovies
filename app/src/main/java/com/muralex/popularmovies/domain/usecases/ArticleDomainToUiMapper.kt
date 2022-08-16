package com.muralex.popularmovies.domain.usecases

import com.muralex.popularmovies.common.data.EntityMapper
import com.muralex.popularmovies.common.data.Resource
import com.muralex.popularmovies.common.data.ResourceInfo
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.topmovies.common.utils.Constants
import com.muralex.topmovies.common.utils.Constants.DATA_FETCH_ERROR
import com.muralex.topmovies.common.utils.ResourceProvider

import javax.inject.Inject

class ArticleDomainToUiMapper @Inject constructor(
   private val  resourceProvider: ResourceProvider
) :
    EntityMapper<Resource<List<Article>>, Resource<List<Article>>> {

    override fun mapFromEntity(data: Resource<List<Article>>): Resource<List<Article>> {
       return if (data.isError()) setErrorMessage(data)
       else data
    }

    private fun setErrorMessage(data: Resource<List<Article>>) : Resource<List<Article>> {
        val errorType = when (data.getInfo()) {
            is ResourceInfo.ConnectionError -> Constants.DataErrors.CONNECTION
            is ResourceInfo.RequestError -> Constants.DataErrors.REQUEST
            is ResourceInfo.ErrorException -> Constants.DataErrors.SERVER
            else -> Constants.DataErrors.GENERIC
        }

        val message = resourceProvider.getErrorMessage(errorType)

        return Resource.error(message, data = data.data).apply {
            setInfo(ResourceInfo.ErrorType(errorType))
        }
    }
}