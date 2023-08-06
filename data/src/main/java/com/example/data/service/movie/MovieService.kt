package com.example.data.service.movie

import com.example.data.network.model.MovieDto
import com.example.data.network.response.BaseListResponse

interface MovieService {

    suspend fun getPopularMovies(
        key: String,
        page: Int,
        limit: Int,
    ): BaseListResponse<List<MovieDto>>

    suspend fun getMovie(
        movieId: String,
        key: String,
    ): MovieDto
}
