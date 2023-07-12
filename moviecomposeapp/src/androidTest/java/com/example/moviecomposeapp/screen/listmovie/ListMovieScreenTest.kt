package com.example.moviecomposeapp.screen.listmovie

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.entity.MovieEntity
import com.example.moviecomposeapp.constant.TEST_TAG_LAZY_COLUMN_MOVIE
import com.example.moviecomposeapp.constant.TEST_TAG_LOADING_ITEM
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListMovieScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val textRefreshString = "Refresh"
    private val textRetryString = "Retry"

    @Test
    fun getListMovieInitialLoadingState() {
        // Given
        val lazyPagingItems = flowOf<PagingData<MovieEntity>>(
            PagingData.empty(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Loading,
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            ListMovieScreen(
                listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                onMovieItemClicked = {},
                isDarkTheme = false,
                onChangeTheme = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithTag(TEST_TAG_LOADING_ITEM)
            .assertExists()
    }

    @Test
    fun getListMovieInitialError() {
        // Given
        val lazyPagingItems = flowOf<PagingData<MovieEntity>>(
            PagingData.empty(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.Error(Exception()),
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            ListMovieScreen(
                listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                onMovieItemClicked = {},
                isDarkTheme = false,
                onChangeTheme = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithText(text = textRefreshString)
            .assertExists()
    }

    @Test
    fun getListMovieLoadMore() {
        // Given
        val lazyPagingItems = flowOf<PagingData<MovieEntity>>(
            PagingData.empty(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.Loading,
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            ListMovieScreen(
                listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                onMovieItemClicked = {},
                isDarkTheme = false,
                onChangeTheme = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithTag(TEST_TAG_LOADING_ITEM)
            .assertExists()
    }

    @Test
    fun getListMovieLoadMoreError() {
        // Given
        val lazyPagingItems = flowOf<PagingData<MovieEntity>>(
            PagingData.empty(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.Error(Exception()),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            ListMovieScreen(
                listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                onMovieItemClicked = {},
                isDarkTheme = false,
                onChangeTheme = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithText(text = textRetryString)
            .assertExists()
    }

    @Test
    fun getListMovieAppendItem() {
        // Given
        val movie1 = MovieEntity(id = 1, title = "Spider man", posterPath = "")
        val movie2 = MovieEntity(id = 2, title = "Luffy", posterPath = "")
        val movie3 = MovieEntity(id = 3, title = "Naruto", posterPath = "")
        val movie4 = MovieEntity(id = 4, title = "Ichigo", posterPath = "")
        val movie5 = MovieEntity(id = 5, title = "Natsu", posterPath = "")
        val movieItems = listOf(movie1, movie2, movie3, movie4, movie5)

        val lazyPagingItems = flowOf(
            PagingData.from(
                data = movieItems,
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            ListMovieScreen(
                listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                onMovieItemClicked = {},
                isDarkTheme = false,
                onChangeTheme = {},
            )
        }

        // Then

        // Check first item
        composeTestRule
            .onNodeWithText(movie1.title!!)
            .assertExists()

        // Scroll to final position
        composeTestRule
            .onNodeWithTag(TEST_TAG_LAZY_COLUMN_MOVIE)
            .performScrollToIndex(movieItems.size - 1)

        // Check bottom item
        composeTestRule
            .onNodeWithText(movie5.title!!)
            .assertExists()
    }
}
