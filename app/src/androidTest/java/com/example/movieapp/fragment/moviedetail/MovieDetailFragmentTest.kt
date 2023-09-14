package com.example.movieapp.fragment.moviedetail

import androidx.core.os.bundleOf
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.movieapp.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@HiltAndroidTest
class MovieDetailFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun showMovieDetailFragment() {
        val bundle = bundleOf("movieId" to "1")
        launchFragmentInHiltContainer<MovieDetailFragment>(fragmentArgs = bundle)
    }
}
