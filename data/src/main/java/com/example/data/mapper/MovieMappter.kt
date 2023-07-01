package com.example.data.mapper

import com.example.data.network.model.MovieDto
import com.example.domain.entity.MovieEntity

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        posterPath = posterPath,
        title = title,
    )
}
