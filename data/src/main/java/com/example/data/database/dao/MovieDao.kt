package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.MovieRecord

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieRecord: MovieRecord): Long

    @Query(value = "SELECT * FROM movie WHERE id = :movieId")
    fun getMovieRecord(movieId: String): MovieRecord?

    @Query(value = "DELETE FROM movie")
    fun clearAll()
}
