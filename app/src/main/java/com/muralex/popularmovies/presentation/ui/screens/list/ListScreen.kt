package com.muralex.popularmovies.presentation.ui.screens.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ListScreen(
    navigateToDetail : (Int) -> Unit
) {
    Scaffold(
        content = {}
    )

}

@Composable
@Preview
private fun ListScreenPreview () {
    ListScreen(navigateToDetail = {})
}
