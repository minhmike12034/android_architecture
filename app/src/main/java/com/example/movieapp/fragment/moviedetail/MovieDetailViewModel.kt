package com.example.movieapp.fragment.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.DispatcherProvider
import com.example.domain.either.Either
import com.example.domain.usecase.GetMovieUseCase
import com.example.movieapp.fragment.moviedetail.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val dispatcherProvider: DispatcherProvider,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId = savedStateHandle.get<String>("movieId") as String

    private val _movieState = MutableStateFlow<MovieState>(MovieState.Loading)
    val movieState = _movieState.asStateFlow()

    init {
        getMovie()
    }

    fun getMovie() {
        viewModelScope.launch(dispatcherProvider.io()) {
            when (val either = getMovieUseCase.execute(movieId)) {
                is Either.Success -> {
                    _movieState.value = MovieState.GetMovieDetailSuccess(either.value)
                }

                is Either.Fail -> {
                    _movieState.value = MovieState.GetMovieDetailFailure(either.value)
                }
            }
        }
    }
}
