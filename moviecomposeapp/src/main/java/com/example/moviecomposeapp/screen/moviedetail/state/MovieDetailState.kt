package com.example.moviecomposeapp.screen.moviedetail.state

import com.example.domain.entity.MovieDetailEntity

sealed class MovieDetailState {
    object Loading : MovieDetailState()
    class GetMovieDetailSuccess(val movieDetailEntity: MovieDetailEntity) : MovieDetailState()
    object GetMovieDetailFailure : MovieDetailState()
}
