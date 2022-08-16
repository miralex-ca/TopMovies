package com.muralex.popularmovies.presentation.ui.screens.list

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.muralex.popularmovies.R
import com.muralex.popularmovies.presentation.ui.theme.topBarColor

@Composable
fun ListAppBar(

) {
    TopAppBar (
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colors.topBarColor,
        contentColor = MaterialTheme.colors.onSurface
    )
}

@Composable
@Preview
private fun ListAppBarPreview () {
    ListAppBar()
}