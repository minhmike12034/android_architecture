package com.example.domain.error

sealed class ErrorEntity {
    // Data Error entity
    object NetworkErrorEntity : ErrorEntity()
    object DatabaseErrorEntity : ErrorEntity()

    // Unknown Error
    object UnknownErrorEntity : ErrorEntity()

    // Logic Error Entity
    sealed class LogicEntity : ErrorEntity()
    object LogicAEntity : LogicEntity()
    object LogicBEntity : LogicEntity()
}
