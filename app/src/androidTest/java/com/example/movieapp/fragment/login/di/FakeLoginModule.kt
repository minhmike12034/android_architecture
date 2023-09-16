package com.example.movieapp.fragment.login.di

import com.example.domain.usecase.login.LoginUseCase
import com.example.movieapp.fragment.login.di.LoginModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LoginModule::class],
)
abstract class FakeLoginModule {

    @Binds
    @Singleton
    abstract fun bindLoginUseCase(
        fakeLoginUseCaseImpl: FakeLoginUseCaseImpl,
    ): LoginUseCase
}
