package com.example.moviecomposeapp.screen.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.DispatcherProvider
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.moviecomposeapp.screen.moviedetail.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val dispatcherProvider: DispatcherProvider,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId = savedStateHandle.get<String>("movieId") as String

    private val _movieDetailState =
        MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)

    val movieDetailState = _movieDetailState.asStateFlow()

    init {
        getMovieDetail()
    }

    fun getMovieDetail() {
        viewModelScope.launch(dispatcherProvider.io()) {
            getMovieDetailUseCase.execute(movieId)
                .onSuccess {
                    _movieDetailState.value = MovieDetailState.GetMovieDetailSuccess(it)
                }
                .onFailure {
                    _movieDetailState.value =
                        MovieDetailState.GetMovieDetailFailure
                }
        }
    }
}
