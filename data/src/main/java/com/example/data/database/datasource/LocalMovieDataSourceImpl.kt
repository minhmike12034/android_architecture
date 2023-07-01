package com.example.data.database.datasource

import com.example.data.database.dao.MovieDao
import com.example.data.database.model.MovieRecord
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : LocalMovieDataSource {
    override suspend fun getMovieRecord(movieId: String): MovieRecord? {
        return movieDao.getMovieRecord(movieId = movieId)
    }

    override suspend fun saveMovieRecord(movieRecord: MovieRecord) {
        movieDao.insertMovie(movieRecord)
    }
}
