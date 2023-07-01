package com.example.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.BuildConfig
import com.example.data.database.datasource.LocalMovieDataSource
import com.example.data.database.model.MovieRecord
import com.example.data.mapper.toMovieEntity
import com.example.data.mapper.toMovieRecord
import com.example.data.network.api.MovieService
import com.example.data.paging.MoviePagingSource
import com.example.domain.entity.MovieEntity
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val localMovieDataSource: LocalMovieDataSource,
) : MovieRepository {

    override fun getPagingPopularMovies(): Flow<PagingData<MovieEntity>> {
        return Pager(PagingConfig(pageSize = 1)) {
            MoviePagingSource(movieService)
        }.flow
    }

    override suspend fun getMovie(movieId: String): MovieEntity? {
        return try {
            // Get movie from server
            val movieDto = movieService.getMovie(movieId = movieId, key = BuildConfig.API_KEY)

            // Save to database
            saveMovieRecord(movieDto.toMovieRecord())

            movieDto.toMovieEntity()
        } catch (e: Exception) {
            // Get data from database in case receive failure from server
            getMovieRecord(movieId)?.toMovieEntity()
        }
    }

    private suspend fun saveMovieRecord(movieRecord: MovieRecord) {
        localMovieDataSource.saveMovieRecord(movieRecord)
    }

    private suspend fun getMovieRecord(movieId: String): MovieRecord? {
        return localMovieDataSource.getMovieRecord(movieId)
    }
}
