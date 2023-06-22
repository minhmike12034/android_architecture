package com.example.data.di

import com.example.data.network.DispatcherProvider
import com.example.data.network.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    fun providesDispatcher(): DispatcherProvider = DispatcherProviderImpl()
}
