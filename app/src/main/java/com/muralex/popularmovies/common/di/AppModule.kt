package com.muralex.popularmovies.common.di

import android.content.Context
import com.muralex.topmovies.common.utils.NetworkHelper
import com.muralex.topmovies.common.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    fun provideResourceProvider(@ApplicationContext appContext: Context) : ResourceProvider {
        return ResourceProvider.Base(appContext)
    }

    @Provides
    fun provideNetworkHelper(@ApplicationContext appContext: Context) : NetworkHelper {
        return NetworkHelper(appContext)
    }


}