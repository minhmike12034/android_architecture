package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.MovieDao
import com.example.data.database.model.MovieRecord

@Database(
    entities = [
        MovieRecord::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        const val MovieTable = "movie"
    }

    abstract fun movieDao(): MovieDao
}
