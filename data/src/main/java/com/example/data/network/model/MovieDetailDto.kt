package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetailDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("vote_average")
    val voteAverage: Double,

)
