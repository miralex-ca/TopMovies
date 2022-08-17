package com.muralex.popularmovies.presentation.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.muralex.popularmovies.presentation.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.delay

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToDetail : (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        delay(700)
        sharedViewModel.getArticles()
    }

    val state = sharedViewModel.viewState.collectAsState()
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 ListAppBar()
        },
        content = {
            ListContent(
                articlesList = state.value,
                navigateToDetail,
                scaffoldState
            ) { sharedViewModel.updateArticles() }
        }
    )
}

