package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPagingPopularMovies(): Flow<PagingData<MovieEntity>>
}
