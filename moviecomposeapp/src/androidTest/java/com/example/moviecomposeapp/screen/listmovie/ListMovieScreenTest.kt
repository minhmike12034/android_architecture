package com.example.moviecomposeapp.screen.listmovie

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.design.theme.MovieAppTheme
import com.example.domain.entity.MovieEntity
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.constant.CONTENT_DESCRIPTION_DARK_MODE
import com.example.moviecomposeapp.constant.CONTENT_DESCRIPTION_LIGHT_MODE
import com.example.moviecomposeapp.constant.TEST_TAG_LAZY_COLUMN_MOVIE
import com.example.moviecomposeapp.constant.TEST_TAG_LOADING_ITEM
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListMovieScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val movie1 = MovieEntity(id = "1", title = "Spider man", posterPath = "")
    private val movie2 = MovieEntity(id = "2", title = "Luffy", posterPath = "")
    private val movie3 = MovieEntity(id = "3", title = "Naruto", posterPath = "")
    private val movie4 = MovieEntity(id = "4", title = "Ichigo", posterPath = "")
    private val movie5 = MovieEntity(id = "5", title = "Natsu", posterPath = "")
    private val movieItems = listOf(movie1, movie2, movie3, movie4, movie5)

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
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = false,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
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
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = false,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithText(text = composeTestRule.activity.getString(R.string.refresh))
            .assertExists()
    }

    @Test
    fun getListMovieLoadMore() {
        // Given
        val lazyPagingItems = flowOf(
            PagingData.from(
                data = movieItems,
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.Loading,
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = false,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
        }

        // Then

        // Scroll to final position
        composeTestRule
            .onNodeWithTag(TEST_TAG_LAZY_COLUMN_MOVIE)
            .performScrollToIndex(movieItems.size - 1)

        composeTestRule
            .onNodeWithTag(TEST_TAG_LOADING_ITEM)
            .assertExists()
    }

    @Test
    fun getListMovieLoadMoreError() {
        // Given
        val lazyPagingItems = flowOf(
            PagingData.from(
                data = movieItems,
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.Error(Exception()),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        )

        composeTestRule.setContent {
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = false,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
        }

        // Then

        // Scroll to final position
        composeTestRule
            .onNodeWithTag(TEST_TAG_LAZY_COLUMN_MOVIE)
            .performScrollToIndex(movieItems.size - 1)

        composeTestRule
            .onNodeWithText(text = composeTestRule.activity.getString(R.string.retry))
            .assertExists()
    }

    @Test
    fun getListMovieAppendItem() {
        // Given

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
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = false,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
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

    @Test
    fun testLightTheme() {
        // Given
        val lazyPagingItems = flowOf<PagingData<MovieEntity>>(PagingData.empty())

        composeTestRule.setContent {
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = false,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithContentDescription(CONTENT_DESCRIPTION_LIGHT_MODE)
            .assertExists()
    }

    @Test
    fun testDarkTheme() {
        // Given
        val lazyPagingItems = flowOf<PagingData<MovieEntity>>(PagingData.empty())

        composeTestRule.setContent {
            MovieAppTheme {
                ListMovieScreen(
                    listPopularMovies = lazyPagingItems.collectAsLazyPagingItems(),
                    onMovieItemClicked = {},
                    isDarkTheme = true,
                    onChangeTheme = {},
                    onLoginClick = {},
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithContentDescription(CONTENT_DESCRIPTION_DARK_MODE)
            .assertExists()
    }
}
