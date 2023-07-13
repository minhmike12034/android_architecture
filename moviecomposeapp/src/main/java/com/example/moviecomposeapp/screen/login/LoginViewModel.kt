package com.example.moviecomposeapp.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.DispatcherProvider
import com.example.domain.either.Either
import com.example.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginResult = MutableSharedFlow<LoginResult>()
    val loginResult = _loginResult.asSharedFlow()

    fun login() {
        viewModelScope.launch(dispatcherProvider.io()) {
            when (val either = loginUseCase.execute(_userName.value, _password.value)) {
                is Either.Success -> {
                    _loginResult.emit(LoginResult.LoginSuccess(either.value))
                }

                is Either.Fail -> {
                    _loginResult.emit(LoginResult.LoginFail(either.value))
                }
            }
        }
    }

    fun setUserName(value: String) {
        _userName.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }
}
