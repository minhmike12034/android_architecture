package com.example.domain.usecase.login

import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

private const val USERNAME_MIN_LENGTH = 4
private const val PASSWORD_MIN_LENGTH = 4

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : LoginUseCase {

    override suspend fun execute(
        userName: String,
        password: String,
    ): Either<ErrorEntity, UserEntity> {
        return when (userName.length < USERNAME_MIN_LENGTH) {
            true -> Either.fail(ErrorEntity.UserNameValidationErrorEntity)
            else -> when (password.length < PASSWORD_MIN_LENGTH) {
                true -> Either.fail(ErrorEntity.PasswordValidationErrorEntity)
                else -> authRepository.login(userName, password)
            }
        }
    }
}
