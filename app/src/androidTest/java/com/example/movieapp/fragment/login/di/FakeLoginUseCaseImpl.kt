package com.example.movieapp.fragment.login.di

import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity
import com.example.domain.usecase.login.LoginUseCase
import javax.inject.Inject

class FakeLoginUseCaseImpl @Inject constructor() : LoginUseCase {

    override suspend fun execute(
        userName: String,
        password: String,
    ): Either<ErrorEntity, UserEntity> {
        return when {
            userName.isEmpty() -> Either.fail(ErrorEntity.UserNameValidationErrorEntity)
            password.isEmpty() -> Either.fail(ErrorEntity.PasswordValidationErrorEntity)
            userName == "test" && password == "test" -> Either.success(UserEntity("test"))
            else -> Either.fail(ErrorEntity.NetworkErrorEntity("Incorrect information"))
        }
    }
}
