package com.example.moviecomposeapp.di

import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.domain.usecase.impl.GetMovieDetailUseCaseImpl
import com.example.domain.usecase.impl.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPopularMoviesUseCase(
        movieRepository: MovieRepository,
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(movieRepository)
    }

    @Provides
    fun provideGetMovieDetailUseCase(
        movieRepository: MovieRepository,
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCaseImpl(movieRepository)
    }
}
