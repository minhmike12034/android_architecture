package com.example.moviecomposeapp.di

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.getmovie.GetMovieUseCase
import com.example.domain.usecase.getpopularmovie.GetPopularMoviesUseCase
import com.example.domain.usecase.login.LoginUseCase
import com.example.domain.usecase.getmovie.GetMovieUseCaseImpl
import com.example.domain.usecase.getpopularmovie.GetPopularMoviesUseCaseImpl
import com.example.domain.usecase.login.LoginUseCaseImpl
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
    ): GetMovieUseCase {
        return GetMovieUseCaseImpl(movieRepository)
    }

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository,
    ): LoginUseCase {
        return LoginUseCaseImpl(authRepository)
    }
}
