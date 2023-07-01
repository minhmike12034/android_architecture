package com.example.data.network.api

import com.example.data.network.model.MovieDetailDto
import com.example.data.network.model.MovieDto
import com.example.data.network.response.BaseListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET(value = "movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): BaseListResponse<List<MovieDto>>

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("api_key") key: String,
    ): MovieDetailDto
}
