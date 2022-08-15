package com.muralex.popularmovies.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muralex.topmovies.common.utils.Constants
import com.muralex.topmovies.common.utils.Constants.DETAIL_ARGUMENT_KEY
import com.muralex.topmovies.common.utils.Constants.DETAIL_SCREEN
import com.muralex.topmovies.common.utils.Constants.LIST_ARGUMENT_KEY
import com.muralex.topmovies.common.utils.Constants.LIST_SCREEN


fun NavGraphBuilder.detailComposable(
    navigateToListScreen: (Constants.NaviAction) -> Unit
) {
    composable(
        route = DETAIL_SCREEN,
        arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }


}