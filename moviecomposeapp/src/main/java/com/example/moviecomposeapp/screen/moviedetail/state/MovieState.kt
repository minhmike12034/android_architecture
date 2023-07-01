package com.example.moviecomposeapp.screen.moviedetail.state

import com.example.domain.entity.MovieEntity

sealed class MovieState {
    object Loading : MovieState()
    class GetMovieDetailSuccess(val movieEntity: MovieEntity) : MovieState()
    object GetMovieDetailFailure : MovieState()
}
