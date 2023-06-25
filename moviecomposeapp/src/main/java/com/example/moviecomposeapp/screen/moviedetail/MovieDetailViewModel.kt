package com.example.moviecomposeapp.screen.moviedetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.moviecomposeapp.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    handle: SavedStateHandle,
) : ViewModel() {
    private val movieId = handle.get<String>(NavRoute.MovieDetail.movieId) ?: ""

    init {
        Log.e("minh", "movie id $movieId")
    }
}
