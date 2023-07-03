package com.example.moviecomposeapp.screen.listmovie

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.entity.MovieEntity
import com.example.moviecomposeapp.screen.moviedetail.MovieDetailScreen
import com.example.moviecomposeapp.screen.moviedetail.state.MovieState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListMovieScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun movieDetailScreenLoading() {
        composeTestRule.setContent {
            MovieDetailScreen(
                movieState = MovieState.Loading,
                onRefreshClick = {},
                onBackClick = {},
            )
        }
    }

    @Test
    fun movieDetailScreenSuccess() {
        composeTestRule.setContent {
            val movie = MovieEntity(
                1,
                "https://wallpapercg.com/media/ts_orig/12093.webp",
                "Title",
                "Overview",
                "Status",
            )
            MovieDetailScreen(
                movieState = MovieState.GetMovieDetailSuccess(movie),
                onRefreshClick = {},
                onBackClick = {},
            )
        }
    }
}
