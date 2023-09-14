package com.example.moviecomposeapp.screen.listmovie.di

import com.example.domain.usecase.getpopularmovie.GetPopularMoviesUseCase
import com.example.domain.usecase.getpopularmovie.GetPopularMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ListMovieModule {

    @Binds
    abstract fun bindGetPopularMoviesUseCase(
        getPopularMoviesUseCaseImpl: GetPopularMoviesUseCaseImpl,
    ): GetPopularMoviesUseCase
}
