package com.example.domain.entity

data class MovieDetailEntity(
    val id: Long,
    val posterPath: String? = null,
    val title: String? = null,
    val overview: String? = null,
    val status: String? = null,
) {
    val imageUrl = "https://image.tmdb.org/t/p/original$posterPath"
}
