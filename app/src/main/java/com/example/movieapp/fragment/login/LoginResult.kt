package com.example.movieapp.fragment.login

import com.example.domain.entity.UserEntity
import com.example.domain.error.ErrorEntity

sealed class LoginResult {
    class LoginSuccess(val userEntity: UserEntity) : LoginResult()
    class LoginFail(val errorEntity: ErrorEntity) : LoginResult()
}
