package com.example.data.di

import com.example.data.network.api.MovieService
import com.example.data.repository.impl.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieService: MovieService,
    ): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}
