package com.example.moviecomposeapp.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeapp.screen.moviedetail.MovieDetailScreen
import com.example.moviecomposeapp.screen.moviedetail.MovieDetailViewModel

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    MovieDetailScreen(
        viewModel = viewModel,
        onBackClick = onBackClick,
    )
}
