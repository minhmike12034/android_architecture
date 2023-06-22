package com.example.domain.entity

data class MovieEntity(
    val id: Long,
    val posterPath: String? = null,
    val overview: String,
    val releaseDate: String? = null,
    val originalTitle: String,
    val title: String,
) {
    val imageUrl = "https://image.tmdb.org/t/p/original$posterPath"
}
