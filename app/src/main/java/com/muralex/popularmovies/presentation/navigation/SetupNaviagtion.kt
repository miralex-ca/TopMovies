package com.muralex.popularmovies.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember



import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.muralex.popularmovies.presentation.navigation.destinations.detailComposable
import com.muralex.popularmovies.presentation.navigation.destinations.listComposable
import com.muralex.popularmovies.presentation.ui.viewmodel.SharedViewModel
import com.muralex.topmovies.common.utils.Constants.LIST_SCREEN

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(
            navigateToDetail = screen.detail,
            sharedViewModel = sharedViewModel
        )
        detailComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.list
        )
    }

}
