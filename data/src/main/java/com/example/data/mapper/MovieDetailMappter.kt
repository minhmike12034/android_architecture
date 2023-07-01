package com.example.data.mapper

import com.example.data.database.model.MovieDetailRecord
import com.example.data.network.model.MovieDetailDto
import com.example.domain.entity.MovieDetailEntity

fun MovieDetailDto.toMovieDetailEntity(): MovieDetailEntity {
    return MovieDetailEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        overview = overview,
        status = status,
    )
}

fun MovieDetailRecord.toMovieDetailEntity(): MovieDetailEntity {
    return MovieDetailEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        overview = overview,
        status = status,
    )
}
