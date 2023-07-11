package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.either.Either
import com.example.domain.entity.MovieEntity
import com.example.domain.error.ErrorEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPagingPopularMovies(): Flow<PagingData<MovieEntity>>
    suspend fun getMovie(movieId: String): Either<ErrorEntity, MovieEntity>
}
