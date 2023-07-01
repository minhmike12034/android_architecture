package com.example.movieapp.fragment.moviedetail.state

import com.example.domain.entity.MovieEntity

sealed class MovieState {
    object Loading : MovieState()
    class GetMovieDetailSuccess(val movieEntity: MovieEntity) : MovieState()
    class GetMovieDetailFailure(val throwable: Throwable) : MovieState()
}
