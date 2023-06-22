package com.example.data.network.api

import com.example.data.network.model.MovieDetailDto
import com.example.data.network.model.MovieDto
import com.example.data.network.response.BaseListResponse

interface MovieService {

    suspend fun getPopularMovies(
        key: String,
        page: Int,
        limit: Int,
    ): BaseListResponse<List<MovieDto>?>

    suspend fun getMovieDetail(
        movieId: String,
        key: String,
    ): MovieDetailDto?
}
