package com.example.moviecomposeapp.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviecomposeapp.screen.listmovie.ListMovieScreen
import com.example.moviecomposeapp.screen.listmovie.ListMovieViewModel

@Composable
fun ListMovieRoute(
    viewModel: ListMovieViewModel = hiltViewModel(),
    onMovieItemClicked: (movieId: String) -> Unit,
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    val listPopularMovies = viewModel.listPopularMovies.collectAsLazyPagingItems()
    ListMovieScreen(
        listPopularMovies = listPopularMovies,
        onMovieItemClicked = onMovieItemClicked,
        isDarkTheme = isDarkTheme,
        onChangeTheme = onChangeTheme,
    )
}
