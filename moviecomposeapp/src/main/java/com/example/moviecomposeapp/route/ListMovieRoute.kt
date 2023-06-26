package com.example.moviecomposeapp.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeapp.screen.listmovie.ListMovieScreen
import com.example.moviecomposeapp.screen.listmovie.ListMovieViewModel

@Composable
fun ListMovieRoute(
    viewModel: ListMovieViewModel = hiltViewModel(),
    onMovieItemClicked: (movieId: String) -> Unit,
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    ListMovieScreen(
        viewModel = viewModel,
        onMovieItemClicked = onMovieItemClicked,
        isDarkTheme = isDarkTheme,
        onChangeTheme = onChangeTheme,
    )
}
