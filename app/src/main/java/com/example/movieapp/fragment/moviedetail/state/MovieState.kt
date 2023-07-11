package com.example.movieapp.fragment.moviedetail.state

import com.example.domain.entity.MovieEntity
import com.example.domain.error.ErrorEntity

sealed class MovieState {
    object Loading : MovieState()
    class GetMovieDetailSuccess(val movieEntity: MovieEntity) : MovieState()
    class GetMovieDetailFailure(val errorEntity: ErrorEntity) : MovieState()
}
