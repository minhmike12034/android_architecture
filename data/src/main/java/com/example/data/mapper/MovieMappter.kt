package com.example.data.mapper

import com.example.data.database.model.MovieRecord
import com.example.data.network.model.MovieDto
import com.example.domain.entity.MovieEntity

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        posterPath = posterPath,
        overview = overview,
        releaseDate = releaseDate,
        originalTitle = originalTitle,
        title = title,
    )
}

fun MovieRecord.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        posterPath = posterPath,
        overview = overview,
        releaseDate = releaseDate,
        originalTitle = originalTitle,
        title = title,
    )
}

fun MovieDto.toMovieRecord(): MovieRecord {
    return MovieRecord(
        id = id,
        posterPath = posterPath,
        overview = overview,
        releaseDate = releaseDate,
        originalTitle = originalTitle,
        title = title,
    )
}
