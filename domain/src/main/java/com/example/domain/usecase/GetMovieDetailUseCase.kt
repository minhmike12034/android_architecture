package com.example.domain.usecase

import com.example.domain.entity.MovieDetailEntity

interface GetMovieDetailUseCase {
    suspend fun execute(movieId: String): Result<MovieDetailEntity>
}
