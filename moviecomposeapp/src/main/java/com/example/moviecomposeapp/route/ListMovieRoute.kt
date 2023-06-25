package com.example.moviecomposeapp.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeapp.screen.listmovie.ListMovieScreen
import com.example.moviecomposeapp.screen.listmovie.ListMovieViewModel

@Composable
fun ListMovieRoute(
    viewModel: ListMovieViewModel = hiltViewModel(),
    onMovieItemClicked: (movieId: String) -> Unit,
) {
    ListMovieScreen(
        viewModel = viewModel,
        onMovieItemClicked = onMovieItemClicked,
    )
}
