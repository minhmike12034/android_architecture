package com.example.moviecomposeapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.design.component.MovieSolidButton
import com.example.moviecomposeapp.R

@Composable
fun ErrorItem(
    modifier: Modifier,
    onRefresh: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        MovieSolidButton(
            modifier = Modifier,
            text = stringResource(R.string.refresh),
        ) {
            onRefresh.invoke()
        }
    }
}
