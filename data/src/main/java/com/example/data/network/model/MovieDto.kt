package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("title")
    val title: String,
)
