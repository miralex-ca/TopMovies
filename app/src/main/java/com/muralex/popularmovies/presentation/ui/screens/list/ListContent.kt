package com.muralex.popularmovies.presentation.ui.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muralex.popularmovies.R
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.presentation.ui.theme.LARGE_PADDING
import com.muralex.popularmovies.presentation.ui.theme.articleBackgroundColor
import com.muralex.popularmovies.presentation.ui.viewmodel.SharedViewModel.ViewState
import com.muralex.topmovies.common.utils.Constants

@ExperimentalMaterialApi
@Composable
fun ListContent(
    articlesList: ViewState,
    navigateToDetail: (detailId: Int) -> Unit,
    scaffoldState: ScaffoldState,
    updateNews: () -> Unit,
    ) {

    when (articlesList) {
        is ViewState.ListLoadFailure -> {
            EmptyContent(
                updateNews
            )
            DisplaySnackBar(
                scaffoldState,
                articlesList.message,
                Constants.NaviAction.DISPLAY
            )
        }
        is ViewState.ListLoaded -> {
            DisplayItems(
                articlesList = articlesList.data.data ?: emptyList(),
                navigateToDetail = navigateToDetail
            )
        }
        is ViewState.ListRefreshed -> {
            DisplayItems(
                articlesList = articlesList.data.data ?: emptyList(),
                navigateToDetail = navigateToDetail
            )
        }
        ViewState.Loading -> {
            LoadingContent()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DisplayItems(
    articlesList: List<Article>,
    navigateToDetail: (detailId: Int) -> Unit,
) {

    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 12.dp,
                end = 12.dp,
                top = 12.dp,
                bottom = 60.dp

            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .widthIn(0.dp, 550.dp)

        ) {

            items(
                items = articlesList,
                key = { article ->
                    article.id
                }) { article ->

                ArticleItem(
                    article = article,
                    navigateToDetail = navigateToDetail)
            }
        }
    }



}

@ExperimentalMaterialApi
@Composable
fun ArticleItem(
    article: Article,
    navigateToDetail: (detailId: Int) -> Unit,
) {
    Surface(modifier = Modifier
        .fillMaxWidth(),
        color = MaterialTheme.colors.articleBackgroundColor,
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp,
        onClick = {
            navigateToDetail(article.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.thumbNail)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_load),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )

            Text(
                text = article.title,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 12.dp, start = 3.dp)

            )

            Row (
                modifier = Modifier
                    .padding(top = 4.dp, start = 3.dp)
                    .alpha(0.9f)
                    ) {

                Text(
                    text = stringResource(R.string.rating, article.vote),

                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = article.releaseMonth,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 14.dp)
                )
            }

        }

    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemPreview() {
    ArticleItem(
        article = Article(0, "title", "text", "", "", "", "", 0.0),
        navigateToDetail = {}
    )
}