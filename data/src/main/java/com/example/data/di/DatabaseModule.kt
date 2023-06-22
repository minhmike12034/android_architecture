package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.MovieDatabase
import com.example.data.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        "movie_database",
    ).build()

    @Provides
    fun provideMoviesDao(
        database: MovieDatabase,
    ): MovieDao = database.movieDao()
}
