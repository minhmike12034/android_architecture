package com.example.movieapp.fragment.moviedetail.di

import com.example.domain.usecase.getmovie.GetMovieUseCase
import com.example.domain.usecase.getmovie.GetMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDetailModule {

    @Binds
    abstract fun bindGetMovieDetailUseCase(
        getMovieUseCaseImpl: GetMovieUseCaseImpl,
    ): GetMovieUseCase
}
