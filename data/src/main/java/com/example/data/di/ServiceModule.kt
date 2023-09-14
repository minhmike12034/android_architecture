package com.example.data.di

import com.example.data.service.movie.MovieService
import com.example.data.service.movie.MovieServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Singleton
    @Binds
    abstract fun bindMovieService(
        movieServiceImpl: MovieServiceImpl,
    ): MovieService
}
