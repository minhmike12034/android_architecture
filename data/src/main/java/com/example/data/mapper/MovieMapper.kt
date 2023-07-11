package com.example.data.mapper

import com.example.data.database.model.MovieRecord
import com.example.data.network.model.MovieDto
import com.example.domain.entity.MovieEntity

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        overview = overview,
        status = status,
    )
}

fun MovieDto.toMovieRecord(): MovieRecord {
    return MovieRecord(
        id = id,
        posterPath = posterPath,
        title = title,
        overview = overview,
        status = status,
    )
}

fun MovieRecord.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        overview = overview,
        status = status,
    )
}
