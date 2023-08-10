package com.example.domain.entity

data class MovieEntity(
    val id: String,
    val posterPath: String? = null,
    val title: String? = null,
    val overview: String? = null,
    val status: String? = null,
) {
    val imageUrl = "https://image.tmdb.org/t/p/original$posterPath"
}
