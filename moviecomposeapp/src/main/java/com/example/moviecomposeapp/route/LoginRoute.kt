package com.example.moviecomposeapp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecomposeapp.screen.login.LoginScreen
import com.example.moviecomposeapp.screen.login.LoginViewModel

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val userName = viewModel.userName.collectAsState()
    val password = viewModel.password.collectAsState()
    val loginResult = viewModel.loginResult.collectAsState(initial = null)

    LoginScreen(
        userName = userName.value,
        password = password.value,
        onUserNameChanged = {
            viewModel.setUserName(it)
        },
        onPasswordChanged = {
            viewModel.setPassword(it)
        },
        loginResult = loginResult.value,
        onLoginClick = {
            viewModel.login()
        },
        onBackClick = onBackClick,
    )
}
