package com.muralex.popularmovies.presentation.ui.screens.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.muralex.popularmovies.R

@Composable
fun ListAppBar(

) {
    TopAppBar (
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.primary
    )
    
}

@Composable
@Preview
private fun ListAppBarPreview () {
    ListAppBar()
}