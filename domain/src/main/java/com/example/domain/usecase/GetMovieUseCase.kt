package com.example.domain.usecase

import com.example.domain.entity.MovieEntity

interface GetMovieUseCase {
    suspend fun execute(movieId: String): Result<MovieEntity>
}
