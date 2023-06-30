package com.example.movieapp.fragment.moviedetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.data.network.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    fun data() {
        Log.e("minh", "data ${savedStateHandle.get<String>("movieId")}")
    }
}
