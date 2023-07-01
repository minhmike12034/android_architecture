package com.example.domain.usecase.impl

import com.example.domain.entity.MovieDetailEntity
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieDetailUseCase
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieDetailUseCase {

    override suspend fun execute(movieId: String): Result<MovieDetailEntity> {
        return try {
            Result.success(movieRepository.getMovieDetail(movieId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
