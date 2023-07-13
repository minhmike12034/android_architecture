package com.example.domain.usecase

import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity

interface LoginUseCase {
    suspend fun execute(userName: String, password: String): Either<ErrorEntity, UserEntity>
}
