package com.example.moviecomposeapp.screen.moviedetail

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.entity.MovieEntity
import com.example.domain.error.ErrorEntity
import com.example.moviecomposeapp.constant.TEST_TAG_LOADING_ITEM
import com.example.moviecomposeapp.screen.moviedetail.state.MovieState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val textRefreshString = "Refresh"

    @Test
    fun getMovieDetailLoadingState() {
        // Given
        val state = MovieState.Loading
        composeTestRule.setContent {
            MovieDetailScreen(
                movieState = state,
                onRefreshClick = {},
                onBackClick = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithTag(TEST_TAG_LOADING_ITEM)
            .assertExists()
    }

    @Test
    fun getMovieDetailSuccessState() {
        // Given
        val movie = MovieEntity(
            1,
            "https://wallpapercg.com/media/ts_orig/12093.webp",
            "Marvel",
            "Spider man",
            "Released",
        )
        val state = MovieState.GetMovieDetailSuccess(movie)
        composeTestRule.setContent {
            MovieDetailScreen(
                movieState = state,
                onRefreshClick = {},
                onBackClick = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithText(text = movie.title!!, substring = true)
            .assertExists()

        composeTestRule
            .onNodeWithText(text = movie.overview!!, substring = true)
            .assertExists()

        composeTestRule
            .onNodeWithText(text = movie.status!!, substring = true)
            .assertExists()
    }

    @Test
    fun getMovieDetailFailureState() {
        // Given
        val state = MovieState.GetMovieDetailFailure(ErrorEntity.NetworkErrorEntity)
        composeTestRule.setContent {
            MovieDetailScreen(
                movieState = state,
                onRefreshClick = {},
                onBackClick = {},
            )
        }

        // Then
        composeTestRule
            .onNodeWithText(text = textRefreshString)
            .assertExists()
    }
}
