package com.example.domain.usecase.impl

import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.LoginUseCase
import javax.inject.Inject

private const val USER_LIMIT_LENGTH = 4
private const val PASSWORD_LIMIT_LENGTH = 4

class LoginUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : LoginUseCase {

    // TODO This is BUSINESS LOGIC -- return LogicErrorEntity
    override suspend fun execute(
        userName: String,
        password: String,
    ): Either<ErrorEntity, UserEntity> {
        return when (userName.length < USER_LIMIT_LENGTH) {
            true -> Either.fail(ErrorEntity.UserNameValidateErrorEntity)
            else -> when (password.length < PASSWORD_LIMIT_LENGTH) {
                true -> Either.fail(ErrorEntity.PasswordValidateErrorEntity)
                else -> authRepository.login(userName, password)
            }
        }
    }
}
