package com.example.domain.usecase.impl

import com.example.domain.entity.MovieEntity
import com.example.domain.error.RecordNotFoundException
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieUseCase
import javax.inject.Inject

class GetMovieUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieUseCase {

    override suspend fun execute(movieId: String): Result<MovieEntity> {
        return try {
            when (val movieEntity = movieRepository.getMovie(movieId)) {
                null -> Result.failure(RecordNotFoundException())
                else -> Result.success(movieEntity)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
