package com.example.domain.usecase.impl

import androidx.paging.PagingData
import com.example.domain.entity.MovieEntity
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetPopularMoviesUseCase {

    override fun execute(): Flow<PagingData<MovieEntity>> {
        return movieRepository.getPagingPopularMovies()
    }
}
