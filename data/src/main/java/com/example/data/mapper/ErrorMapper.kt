package com.example.data.mapper

import com.example.domain.error.ErrorEntity

fun Exception.toDatabaseErrorEntity(): ErrorEntity.DatabaseErrorEntity {
    return ErrorEntity.DatabaseErrorEntity(this.message ?: "")
}

fun Exception.toNetworkErrorEntity(): ErrorEntity.NetworkErrorEntity {
    return ErrorEntity.NetworkErrorEntity(this.message ?: "")
}
