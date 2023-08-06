package com.example.domain.usecase.getpopularmovie

import androidx.paging.PagingData
import com.example.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    fun execute(): Flow<PagingData<MovieEntity>>
}
