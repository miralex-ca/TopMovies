package com.muralex.popularmovies.presentation.ui.screens.detail

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.topmovies.common.utils.Constants

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    selectedArticle: Article,
    navigateToListScreen: (Constants.NaviAction) -> Unit
) {
    Scaffold (
        topBar = {
            DetailAppBar(
                navigateToListScreen = navigateToListScreen,
                article = selectedArticle
            )
        },
        content = {
            DetailContent(
                article = selectedArticle
            )
        }
    )
}