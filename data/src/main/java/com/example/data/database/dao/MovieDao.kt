package com.example.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.MovieDetailRecord

@Dao
interface MovieDao {

    @Query(value = "SELECT * FROM movie ORDER BY id ASC")
    fun getPageMovieRecord(): PagingSource<Int, MovieDetailRecord>

    @Query(value = "DELETE FROM movie")
    fun clearAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovies(movieRecords: List<MovieDetailRecord>): List<Long>
}
