package com.example.movieapp.fragment.listmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.data.network.DispatcherProvider
import com.example.domain.usecase.getpopularmovie.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ListMovieViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    val listPopularMovies = getPopularMoviesUseCase.execute()
        .flowOn(dispatcherProvider.io())
        .cachedIn(viewModelScope)
}
