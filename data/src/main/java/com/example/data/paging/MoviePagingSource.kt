package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.BuildConfig
import com.example.data.mapper.toMovieEntity
import com.example.data.network.api.MovieService
import com.example.domain.entity.MovieEntity
import javax.inject.Inject

private const val PAGE_LIMIT = 20

class MoviePagingSource @Inject constructor(
    private val movieService: MovieService,
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val start = params.key ?: STARTING_PAGE
            val listMovie = getPopularMovies(page = start)
            val key = getKey(start, listMovie)
            LoadResult.Page(
                data = listMovie,
                prevKey = key.first,
                nextKey = key.second,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getPopularMovies(page: Int): List<MovieEntity> {
        return movieService.getPopularMovies(
            key = BuildConfig.API_KEY,
            page = page,
            PAGE_LIMIT,
        ).data
            ?.map { it.toMovieEntity() }
            .orEmpty()
    }

    private fun getKey(start: Int, listMovie: List<MovieEntity>): Pair<Int?, Int?> {
        return Pair(
            when (start) {
                STARTING_PAGE -> null
                else -> start - 1
            },
            when (listMovie.isEmpty()) {
                true -> null
                else -> start + 1
            },
        )
    }

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? = null

    companion object {
        const val STARTING_PAGE = 1
    }
}
