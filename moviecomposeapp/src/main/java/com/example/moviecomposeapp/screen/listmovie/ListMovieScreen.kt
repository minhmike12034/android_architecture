package com.example.moviecomposeapp.screen.listmovie

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.design.component.MovieSolidButton

@Composable
internal fun ListMovieScreen(
    viewModel: ListMovieViewModel,
    onMovieItemClicked: (movieId: String) -> Unit,
) {
    MovieSolidButton(modifier = Modifier.fillMaxWidth(), text = "Navigation to detail screen") {
        onMovieItemClicked.invoke("movieId")
    }
}
