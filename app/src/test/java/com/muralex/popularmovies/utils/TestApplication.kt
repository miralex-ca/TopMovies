package com.muralex.popularmovies.utils

import android.app.Application
import com.muralex.popularmovies.R


class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_PopularMovies)
    }
}