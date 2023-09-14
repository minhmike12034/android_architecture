package com.example.moviecomposeapp.screen.login.di

import com.example.domain.usecase.login.LoginUseCase
import com.example.domain.usecase.login.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl,
    ): LoginUseCase
}
