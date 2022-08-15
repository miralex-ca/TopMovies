package com.muralex.popularmovies.common.di

import com.muralex.topmovies.common.utils.NetworkHelper
import com.muralex.popularmovies.data.api.MoviesApiService
import com.muralex.popularmovies.data.mappers.ApiDataToArticleMapper
import com.muralex.popularmovies.data.repository.RepositoryImpl
import com.muralex.popularmovies.data.repository.cachedatasource.CacheDataSource
import com.muralex.popularmovies.data.repository.cachedatasource.CacheDataSourceImpl
import com.muralex.popularmovies.domain.MovieRepository
import com.muralex.popularmovies.data.repository.remotedaasource.RemoteDataSource
import com.muralex.popularmovies.data.repository.remotedaasource.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsApiService: MoviesApiService,
        networkHelper: NetworkHelper
    ) : RemoteDataSource {
        return RemoteDataSourceImpl(
            apiService = newsApiService,
            mapper = ApiDataToArticleMapper(),
            networkHelper = networkHelper
        )
    }

    @Singleton
    @Provides
    fun provideNewsCacheDataSource() : CacheDataSource {
        return CacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideRepository(
        cacheDataSource: CacheDataSource,
        remoteDataSource: RemoteDataSource,
    ) : MovieRepository {
        return RepositoryImpl(cacheDataSource, remoteDataSource)
    }

}