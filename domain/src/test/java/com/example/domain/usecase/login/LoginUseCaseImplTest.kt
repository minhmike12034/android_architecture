package com.example.domain.usecase.login

import com.example.domain.either.Either
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoginUseCaseImplTest {

    private val authRepository: AuthRepository = mock()

    private val loginUseCase = LoginUseCaseImpl(
        authRepository = authRepository,
    )

    @Test
    fun `given user name length is less than 4, when execute, then return failure with UserNameValidationErrorEntity`() {
        runBlocking {
            // given
            val userName = "abc"
            val password = "1234"

            // when
            val actual = loginUseCase.execute(userName = userName, password = password)

            // then
            val expected = Either.fail(ErrorEntity.UserNameValidationErrorEntity)
            verify(authRepository, never()).login(any(), any())
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `given password length is less than 4, when execute, then return failure with PasswordValidationErrorEntity`() {
        runBlocking {
            // given
            val userName = "john"
            val password = "123"

            // when
            val actual = loginUseCase.execute(userName = userName, password = password)

            // then
            val expected = Either.fail(ErrorEntity.PasswordValidationErrorEntity)
            verify(authRepository, never()).login(any(), any())
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `given both username and password length are valid, when execute, then return result from auth repository login call`() {
        runBlocking {
            // given
            val userName = "john"
            val password = "1234"

            val expected = Either.success(UserEntity(userName = userName))
            whenever(authRepository.login(userName, password)).thenReturn(expected)

            // when
            val actual = loginUseCase.execute(userName = userName, password = password)

            // then
            verify(authRepository, times(1)).login(userName = userName, password = password)
            assertEquals(expected, actual)
        }
    }
}
