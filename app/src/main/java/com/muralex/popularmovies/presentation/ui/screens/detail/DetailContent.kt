package com.muralex.popularmovies.presentation.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muralex.popularmovies.R
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.presentation.ui.theme.LARGE_PADDING
import com.muralex.popularmovies.presentation.ui.theme.articleBackgroundColor


@Composable
fun DetailContent(
    article: Article
) {

    Box(
        modifier= Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter) {

        Surface(
            color = MaterialTheme.colors.articleBackgroundColor,
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp,
            modifier = Modifier
                .padding(
                    start = LARGE_PADDING,
                    end =  LARGE_PADDING,
                    top = LARGE_PADDING,
                    bottom = 60.dp
                )
                .widthIn(0.dp, 500.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(article.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_load),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                )

                Text(
                    text = article.title,
                    color = MaterialTheme.colors.onSurface,
                    style  = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 12.dp, start = 3.dp)
                )

                Row (
                    modifier = Modifier
                        .padding(top = 12.dp, start = 3.dp)
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
                        text = stringResource(R.string.release_date, article.release) ,
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 18.dp)
                    )
                }

                Text(
                    text = article.text,
                    color = MaterialTheme.colors.onSurface,
                    style  = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 3.dp, bottom = 30.dp)

                )

            }

        }


    }
}