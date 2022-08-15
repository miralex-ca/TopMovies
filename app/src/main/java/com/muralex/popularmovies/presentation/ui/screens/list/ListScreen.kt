package com.muralex.popularmovies.presentation.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.presentation.ui.viewmodel.SharedViewModel
import timber.log.Timber


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToDetail : (Int) -> Unit
) {

//    LaunchedEffect(key1 = true) {
//        Timber.d("trigger")
//        sharedViewModel.getNews()
//    }
//
//    val state = sharedViewModel.viewState.collectAsState()
//
//    var items: List<Article>? = emptyList()
//
//
//    when (state.value) {
//        is SharedViewModel.ViewState.ListLoadFailure -> {}
//        is SharedViewModel.ViewState.ListLoaded -> {
//            items = (state.value as SharedViewModel.ViewState.ListLoaded).data.data
//        }
//        is SharedViewModel.ViewState.ListRefreshed -> {
//            items = (state.value as SharedViewModel.ViewState.ListRefreshed).data.data
//        }
//        is SharedViewModel.ViewState.Loading -> {}
//    }
//
//    items?.forEach {
//        Timber.d("item: ${it.title}")
//    }


    Scaffold(
        topBar = {
                 ListAppBar()
        },
        content = {
           // ListContent()
        }
    )

}

