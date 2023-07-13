package com.example.domain.repository

import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity

interface AuthRepository {
    suspend fun login(userName: String, password: String): Either<ErrorEntity, UserEntity>
}
