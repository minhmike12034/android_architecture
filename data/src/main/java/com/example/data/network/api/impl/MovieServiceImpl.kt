package com.example.data.network.api.impl

import com.example.data.network.api.MovieApi
import com.example.data.network.api.MovieService
import com.example.data.network.model.MovieDto
import com.example.data.network.response.BaseListResponse
import javax.inject.Inject

class MovieServiceImpl @Inject constructor(
    private val movieApi: MovieApi,
) : MovieService {

    override suspend fun getPopularMovies(
        key: String,
        page: Int,
        limit: Int,
    ): BaseListResponse<List<MovieDto>> {
        return movieApi.getPopularMovies(key, page, limit)
    }

    override suspend fun getMovie(
        movieId: String,
        key: String,
    ): MovieDto {
        return movieApi.getMovieDetail(movieId, key)
    }
}
