package com.muralex.popularmovies.presentation.navigation


import androidx.navigation.NavHostController
import com.muralex.topmovies.common.utils.Constants
import com.muralex.topmovies.common.utils.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list : (Constants.NaviAction) -> Unit = { navAction ->
        navController.navigate("list/${navAction.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }

    val detail: (Int) -> Unit = { detail_id ->
        navController.navigate("detail/$detail_id")
    }
 }