package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("title")
    val title: String,
)
