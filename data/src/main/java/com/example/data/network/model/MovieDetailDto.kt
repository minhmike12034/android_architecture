package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetailDto(

    @SerializedName("id")
    val id: Long,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("status")
    val status: String,

)
