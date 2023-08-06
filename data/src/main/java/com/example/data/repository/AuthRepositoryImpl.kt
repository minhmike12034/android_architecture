package com.example.data.repository

import com.example.data.mapper.toNetworkErrorEntity
import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    // Fake data
    // TODO This is DATA LOGIC
    override suspend fun login(
        userName: String,
        password: String,
    ): Either<ErrorEntity, UserEntity> {
        return when {
            userName == "admin" && password == "admin" -> {
                Either.Success(UserEntity("admin"))
            }

            else -> {
                // This is fake response from server side
                Either.fail(Exception("Incorrect information").toNetworkErrorEntity())
            }
        }
    }
}
