package com.muralex.popularmovies.presentation.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muralex.popularmovies.presentation.ui.screens.list.ListScreen
import com.muralex.popularmovies.presentation.ui.viewmodel.SharedViewModel
import com.muralex.topmovies.common.utils.Constants.LIST_ARGUMENT_KEY
import com.muralex.topmovies.common.utils.Constants.LIST_SCREEN

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToDetail: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(
            navigateToDetail = navigateToDetail,
            sharedViewModel = sharedViewModel
        )
    }

}