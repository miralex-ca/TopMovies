package com.muralex.popularmovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember



import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.muralex.popularmovies.presentation.navigation.destinations.detailComposable
import com.muralex.popularmovies.presentation.navigation.destinations.listComposable
import com.muralex.topmovies.common.utils.Constants.LIST_SCREEN


@Composable
fun SetupNavigation(
    navController: NavHostController
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToDetail = screen.detail
        )
        detailComposable(
            navigateToListScreen = screen.list
        )
    }

}
