package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.database.MovieDatabase

@Entity(
    tableName = MovieDatabase.MovieTable,
)
data class MovieDetailRecord(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(defaultValue = "")
    val posterPath: String? = null,

    @ColumnInfo(defaultValue = "")
    val title: String,

    @ColumnInfo(defaultValue = "")
    val overview: String,

    @ColumnInfo(defaultValue = "")
    val status: String?,
)
