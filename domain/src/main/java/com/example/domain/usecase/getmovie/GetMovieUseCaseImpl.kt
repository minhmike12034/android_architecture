package com.example.domain.usecase.getmovie

import com.example.domain.either.Either
import com.example.domain.entity.MovieEntity
import com.example.domain.error.ErrorEntity
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieUseCase {

    override suspend fun execute(movieId: String): Either<ErrorEntity, MovieEntity> {
        return movieRepository.getMovie(movieId)
    }
}
