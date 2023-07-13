package com.example.data.di

import com.example.data.database.datasource.LocalMovieDataSource
import com.example.data.network.api.MovieService
import com.example.data.repository.impl.AuthRepositoryImpl
import com.example.data.repository.impl.MovieRepositoryImpl
import com.example.domain.repository.AuthRepository
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
        localMovieDataSource: LocalMovieDataSource,
    ): MovieRepository {
        return MovieRepositoryImpl(movieService, localMovieDataSource)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }
}
