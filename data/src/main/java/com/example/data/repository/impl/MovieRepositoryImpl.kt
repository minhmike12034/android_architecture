package com.example.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.BuildConfig
import com.example.data.mapper.toMovieDetailEntity
import com.example.data.network.api.MovieService
import com.example.data.paging.MoviePagingSource
import com.example.domain.entity.MovieDetailEntity
import com.example.domain.entity.MovieEntity
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
) : MovieRepository {

    override fun getPagingPopularMovies(): Flow<PagingData<MovieEntity>> {
        return Pager(PagingConfig(pageSize = 1)) {
            MoviePagingSource(movieService)
        }.flow
    }

    override suspend fun getMovieDetail(movieId: String): MovieDetailEntity {
        return movieService.getMovieDetail(movieId = movieId, key = BuildConfig.API_KEY)
            .toMovieDetailEntity()
    }
}
