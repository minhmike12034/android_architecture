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
    onLoginClick: () -> Unit,
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    val listPopularMovies = viewModel.listPopularMovies.collectAsLazyPagingItems()
    ListMovieScreen(
        listPopularMovies = listPopularMovies,
        onMovieItemClicked = onMovieItemClicked,
        onLoginClick = onLoginClick,
        isDarkTheme = isDarkTheme,
        onChangeTheme = onChangeTheme,
    )
}
