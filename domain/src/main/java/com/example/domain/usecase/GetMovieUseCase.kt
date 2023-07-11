package com.example.domain.usecase

import com.example.domain.either.Either
import com.example.domain.entity.MovieEntity
import com.example.domain.error.ErrorEntity

interface GetMovieUseCase {
    suspend fun execute(movieId: String): Either<ErrorEntity, MovieEntity>
}
