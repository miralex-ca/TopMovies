package com.muralex.topmovies.common.utils

import android.content.Context
import androidx.annotation.StringRes
import com.muralex.popularmovies.R

import com.muralex.topmovies.common.utils.Constants.DataErrors

interface ResourceProvider {

    fun getString(@StringRes resId: Int) : String
    fun getErrorMessage(errorType: DataErrors) : String

    class Base (private val context: Context) : ResourceProvider {

        override fun getString(resId: Int) : String = context.getString(resId)

        override fun getErrorMessage(errorType: DataErrors): String {
            val string = getString(
                when (errorType) {
                    DataErrors.CONNECTION -> R.string.error_msg_connection_error
                    DataErrors.REQUEST -> R.string.error_msg_request_error
                    DataErrors.SERVER -> R.string.error_msg_service_error
                    else -> R.string.error_msg_generic_error
                }
            )
            return string
        }
    }
}