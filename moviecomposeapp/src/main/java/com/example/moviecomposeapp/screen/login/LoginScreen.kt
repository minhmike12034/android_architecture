package com.example.moviecomposeapp.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.design.component.MovieSolidButton
import com.example.design.dimension.Dimension
import com.example.design.windowinfo.WindowInfo
import com.example.design.windowinfo.rememberWindowInfo
import com.example.domain.error.ErrorEntity
import com.example.moviecomposeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(
    userName: String,
    password: String,
    onUserNameChanged: (value: String) -> Unit,
    onPasswordChanged: (value: String) -> Unit,
    loginResult: LoginResult?,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.login),
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        content = { paddingValues ->
            LoginContent(
                modifier = Modifier.padding(paddingValues),
                userName = userName,
                password = password,
                onUserNameChanged = onUserNameChanged,
                onPasswordChanged = onPasswordChanged,
                onLoginClick = onLoginClick,
                loginResult = loginResult,
            )
        },
    )
}

@Composable
fun HandleLoginResult(loginResult: LoginResult) {
    when (loginResult) {
        is LoginResult.LoginSuccess -> {
            ShowMessage(
                message = stringResource(
                    R.string.user_login_success,
                    loginResult.userEntity.userName,
                ),
            )
        }

        is LoginResult.LoginFail -> {
            when (loginResult.errorEntity) {
                is ErrorEntity.UserNameValidationErrorEntity -> {
                    ShowMessage(message = stringResource(R.string.user_name_is_wrong))
                }

                is ErrorEntity.PasswordValidationErrorEntity -> {
                    ShowMessage(message = stringResource(R.string.password_is_wrong))
                }

                else -> {
                    ShowMessage(
                        message = loginResult.errorEntity.message
                            ?: stringResource(R.string.unknown_message),
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowMessage(
    modifier: Modifier = Modifier,
    message: String,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Red,
        )
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    userName: String,
    password: String,
    onUserNameChanged: (value: String) -> Unit,
    onPasswordChanged: (value: String) -> Unit,
    onLoginClick: () -> Unit,
    loginResult: LoginResult?,
) {
    val windowInfo = rememberWindowInfo()
    when (windowInfo.screenWidthInfo) {
        is WindowInfo.WindowType.Expanded -> {
            LoginContentExpand(
                modifier = modifier,
                userName = userName,
                password = password,
                onUserNameChanged = onUserNameChanged,
                onPasswordChanged = onPasswordChanged,
                onLoginClick = onLoginClick,
                loginResult = loginResult,
            )
        }

        else -> {
            LoginContentCompact(
                modifier = modifier,
                userName = userName,
                password = password,
                onUserNameChanged = onUserNameChanged,
                onPasswordChanged = onPasswordChanged,
                onLoginClick = onLoginClick,
                loginResult = loginResult,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContentCompact(
    modifier: Modifier = Modifier,
    userName: String,
    password: String,
    onUserNameChanged: (value: String) -> Unit,
    onPasswordChanged: (value: String) -> Unit,
    onLoginClick: () -> Unit,
    loginResult: LoginResult?,
) {
    Column(
        modifier = modifier.padding(Dimension.Spacing_20),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = onUserNameChanged,
            placeholder = {
                Text(stringResource(R.string.user_name))
            },
        )

        Spacer(modifier = Modifier.width(Dimension.Spacing_20))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChanged,
            placeholder = {
                Text(stringResource(R.string.password))
            },
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_20))

        MovieSolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
        ) {
            onLoginClick.invoke()
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_20))

        loginResult?.let { HandleLoginResult(it) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContentExpand(
    modifier: Modifier = Modifier,
    userName: String,
    password: String,
    onUserNameChanged: (value: String) -> Unit,
    onPasswordChanged: (value: String) -> Unit,
    onLoginClick: () -> Unit,
    loginResult: LoginResult?,
) {
    Column(
        modifier = modifier.padding(Dimension.Spacing_20),
    ) {
        Row {
            TextField(
                modifier = Modifier.weight(1F),
                value = userName,
                onValueChange = onUserNameChanged,
                placeholder = {
                    Text(stringResource(R.string.user_name))
                },
            )

            Spacer(modifier = Modifier.weight(0.1F))

            TextField(
                modifier = Modifier.weight(1F),
                value = password,
                onValueChange = onPasswordChanged,
                placeholder = {
                    Text(stringResource(R.string.password))
                },
                visualTransformation = PasswordVisualTransformation(),
            )
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_20))

        MovieSolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
        ) {
            onLoginClick.invoke()
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_20))

        loginResult?.let { HandleLoginResult(it) }
    }
}
