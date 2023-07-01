package com.example.moviecomposeapp.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.design.component.MovieSolidButton

@Composable
fun ErrorItem(
    modifier: Modifier,
    onRefresh: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        MovieSolidButton(modifier = Modifier, text = "Refresh") {
            onRefresh.invoke()
        }
    }
}
