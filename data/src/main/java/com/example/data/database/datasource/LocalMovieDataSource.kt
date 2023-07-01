package com.example.data.database.datasource

import com.example.data.database.model.MovieRecord

interface LocalMovieDataSource {
    suspend fun getMovieRecord(movieId: String): MovieRecord?
    suspend fun saveMovieRecord(movieRecord: MovieRecord)
}
