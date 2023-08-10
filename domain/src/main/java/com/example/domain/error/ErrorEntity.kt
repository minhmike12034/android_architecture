package com.example.domain.error

sealed class ErrorEntity(val message: String? = null) {
    // Data Error entity
    class NetworkErrorEntity(message: String) : ErrorEntity(message)
    class DatabaseErrorEntity(message: String) : ErrorEntity(message)

    // Logic Error Entity
    sealed class LogicErrorEntity : ErrorEntity()
    object UserNameValidationErrorEntity : LogicErrorEntity()
    object PasswordValidationErrorEntity : LogicErrorEntity()
}
