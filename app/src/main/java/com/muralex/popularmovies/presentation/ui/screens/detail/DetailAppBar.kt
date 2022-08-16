package com.muralex.popularmovies.presentation.ui.screens.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ShareCompat
import com.muralex.popularmovies.R
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.presentation.ui.theme.articleBackgroundColor
import com.muralex.popularmovies.presentation.ui.theme.topBarColor
import com.muralex.popularmovies.presentation.ui.theme.topBarTextColor
import com.muralex.topmovies.common.utils.Constants

@Composable
fun DetailAppBar(
    article: Article,
    navigateToListScreen: (Constants.NaviAction) -> Unit
) {
    val shareContext = LocalContext.current

    TopAppBar (
        navigationIcon = {
                         BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(text = stringResource(R.string.detail_title))
        },
        backgroundColor = MaterialTheme.colors.topBarColor,
        contentColor = MaterialTheme.colors.onSurface,
        actions = {
            ShareAction(
                onShareClicked = {
                    ShareCompat.IntentBuilder(shareContext)
                        .setType("text/plain")
                        .setChooserTitle(shareContext.getString(R.string.text_share))
                        .setText(shareContext.getString(R.string.share_movie, article.title))
                        .startChooser()
                }
            )
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Constants.NaviAction) -> Unit
) {
    IconButton(onClick = { onBackClicked(Constants.NaviAction.DISPLAY) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun ShareAction(
    onShareClicked: (Constants.NaviAction) -> Unit
) {
    IconButton(onClick = { onShareClicked(Constants.NaviAction.SHARE) }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface
        )
    }
}
