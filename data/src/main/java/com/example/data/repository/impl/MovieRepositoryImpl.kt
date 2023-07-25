package com.example.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.BuildConfig
import com.example.data.database.datasource.LocalMovieDataSource
import com.example.data.database.model.MovieRecord
import com.example.data.mapper.toDatabaseErrorEntity
import com.example.data.mapper.toMovieEntity
import com.example.data.mapper.toMovieRecord
import com.example.data.network.api.MovieService
import com.example.data.paging.MoviePagingSource
import com.example.domain.either.Either
import com.example.domain.entity.MovieEntity
import com.example.domain.error.ErrorEntity
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

    // TODO This is DATA LOGIC
    override suspend fun getMovie(movieId: String): Either<ErrorEntity, MovieEntity> {
        return try {
            // Get movie from server
            val movieDto = movieService.getMovie(movieId = movieId, key = BuildConfig.API_KEY)

            // Save to database
            when (val either = saveMovieRecord(movieDto.toMovieRecord())) {
                is Either.Success -> Either.success(movieDto.toMovieEntity())
                is Either.Fail -> Either.fail(either.value)
            }
        } catch (e: Exception) {
            when (val either = getMovieRecord(movieId)) {
                is Either.Success -> {
                    when (either.value != null) {
                        true -> Either.success(either.value!!.toMovieEntity())
                        else -> Either.fail(ErrorEntity.DatabaseErrorEntity("Cannot found movie record"))
                    }
                }

                is Either.Fail -> Either.fail(either.value)
            }
        }
    }

    private suspend fun saveMovieRecord(movieRecord: MovieRecord): Either<ErrorEntity.DatabaseErrorEntity, Long> {
        return try {
            Either.success(localMovieDataSource.saveMovieRecord(movieRecord))
        } catch (e: Exception) {
            Either.fail(e.toDatabaseErrorEntity())
        }
    }

    private suspend fun getMovieRecord(movieId: String): Either<ErrorEntity, MovieRecord?> {
        return try {
            Either.success(localMovieDataSource.getMovieRecord(movieId))
        } catch (e: Exception) {
            Either.fail(e.toDatabaseErrorEntity())
        }
    }
}
