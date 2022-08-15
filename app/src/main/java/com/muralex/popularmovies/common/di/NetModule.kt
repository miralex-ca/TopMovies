package com.muralex.popularmovies.common.di

import com.muralex.popularmovies.data.api.MoviesApiKeyInterceptor
import com.muralex.popularmovies.data.api.MoviesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideNewsApiKeyInterceptor() = MoviesApiKeyInterceptor("api_key", "adaf25bb200c1ac318d6410be8c2418e")

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: MoviesApiKeyInterceptor,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder().apply {
                addInterceptor(loggingInterceptor)
                addInterceptor(apiInterceptor)
                connectTimeout(30, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
            }.build()
    }


    @Singleton
    @Provides
    fun provideApiService(clientHTTP: OkHttpClient): MoviesApiService {
        return Retrofit.Builder()
            .client(clientHTTP)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }






}