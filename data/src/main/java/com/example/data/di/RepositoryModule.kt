package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository
}
