package com.muralex.popularmovies.presentation.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muralex.popularmovies.data.models.app.Article
import com.muralex.popularmovies.presentation.ui.theme.LARGE_PADDING
import com.muralex.popularmovies.presentation.ui.theme.articleBackgroundColor
import com.muralex.popularmovies.presentation.ui.theme.articleTextColor

@Composable
fun ListContent() {


}

@ExperimentalMaterialApi
@Composable
fun ArticleItem (
    article: Article,
    navigateToDetail: (detailId: Int) -> Unit
) {
    Surface(modifier = Modifier
        .fillMaxWidth(),
        color = MaterialTheme.colors.articleBackgroundColor,
        shape = RectangleShape,
        elevation = 2.dp,
        onClick = {
            navigateToDetail(article.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Text(
                text = article.title,
                color = MaterialTheme.colors.articleTextColor,
                style  = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Text(
                text = article.text,
                color = MaterialTheme.colors.articleTextColor,
                style  = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemPreview () {
    ArticleItem(
        article = Article(0, "title", "text", "", "", 0.0),
        navigateToDetail = {}
    )
}