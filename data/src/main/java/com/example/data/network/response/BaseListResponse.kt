package com.example.data.network.response

import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(
    @SerializedName("results")
    val data: T,

    @SerializedName("page")
    val page: Int,

    @SerializedName("total_results")
    val totalResult: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,
)
