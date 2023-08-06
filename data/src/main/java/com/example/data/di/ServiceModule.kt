package com.example.data.di

import com.example.data.network.api.MovieApi
import com.example.data.service.movie.MovieService
import com.example.data.service.movie.MovieServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideMovieService(
        movieApi: MovieApi,
    ): MovieService {
        return MovieServiceImpl(movieApi)
    }
}
