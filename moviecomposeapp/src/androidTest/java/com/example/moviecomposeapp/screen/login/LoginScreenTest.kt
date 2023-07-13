package com.example.moviecomposeapp.screen.login

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.design.theme.MovieAppTheme
import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity
import com.example.moviecomposeapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginSuccessState() {
        // Given
        val userName = "Mike"
        composeTestRule.setContent {
            MovieAppTheme {
                LoginScreen(
                    userName = userName,
                    password = "123456",
                    onLoginClick = {},
                    onUserNameChanged = {},
                    onPasswordChanged = {},
                    onBackClick = {},
                    loginResult = LoginResult.LoginSuccess(UserEntity(userName)),
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithText(userName)
            .assertExists()

        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(
                    R.string.user_login_success,
                    userName,
                ),
            )
            .assertExists()
    }

    @Test
    fun userNameIsNotEnough() {
        // Given
        val userName = "123"
        composeTestRule.setContent {
            MovieAppTheme {
                LoginScreen(
                    userName = userName,
                    password = "",
                    onLoginClick = {},
                    onUserNameChanged = {},
                    onPasswordChanged = {},
                    onBackClick = {},
                    loginResult = LoginResult.LoginFail(ErrorEntity.UserNameValidateErrorEntity),
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(R.string.user_name_is_wrong),
            )
            .assertExists()
    }

    @Test
    fun passwordIsNotEnough() {
        // Given
        composeTestRule.setContent {
            MovieAppTheme {
                LoginScreen(
                    userName = "userName",
                    password = "",
                    onLoginClick = {},
                    onUserNameChanged = {},
                    onPasswordChanged = {},
                    onBackClick = {},
                    loginResult = LoginResult.LoginFail(ErrorEntity.PasswordValidateErrorEntity),
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithText(
                composeTestRule.activity.getString(R.string.password_is_wrong),
            )
            .assertExists()
    }

    @Test
    fun incorrectLoginInformation() {
        // Given
        val errorMsg = "Incorrect information"
        composeTestRule.setContent {
            MovieAppTheme {
                LoginScreen(
                    userName = "userName",
                    password = "mike",
                    onLoginClick = {},
                    onUserNameChanged = {},
                    onPasswordChanged = {},
                    onBackClick = {},
                    loginResult = LoginResult.LoginFail(ErrorEntity.NetworkErrorEntity(errorMsg)),
                )
            }
        }

        // Then
        composeTestRule
            .onNodeWithText(errorMsg)
            .assertExists()
    }
}
