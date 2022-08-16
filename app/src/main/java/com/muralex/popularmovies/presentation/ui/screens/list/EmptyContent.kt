package com.muralex.popularmovies.presentation.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muralex.popularmovies.R
import com.muralex.popularmovies.presentation.ui.theme.progressColor
import com.muralex.topmovies.common.utils.Constants
import kotlinx.coroutines.launch


@Composable
fun EmptyContent(
    updateNews: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
            ) {

        Text(text = stringResource(R.string.empty_list_text))
        Spacer(modifier = Modifier.height(20.dp))
        Icon(
            modifier = Modifier.size(80.dp)
                .alpha(0.6f),
            tint = Color.White,
            painter = painterResource(id = R.drawable.ic_load_error),
            contentDescription = null)


        Spacer(modifier = Modifier.height(40.dp))

        TextButton(
            onClick = updateNews,
        ) {
            Text(
                text = stringResource(R.string.retry),
                color = Color.White,
                fontSize = 22.sp
            )
            Icon(
                imageVector = Icons.Filled.Refresh,
                tint = Color.White,
                contentDescription = "Localized description",
                modifier = Modifier
                    .padding(start = 8.dp, top = 5.dp)
                    .size(30.dp),
            )
        }
    }
}

@Composable
fun LoadingContent() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.progressColor
        )
    }

}


@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    message: String,
    naviAction: Constants.NaviAction,
) {

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = naviAction) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message =  message
            )
        }
    }

}
