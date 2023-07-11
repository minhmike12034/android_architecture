package com.example.domain.either

sealed class Either<out F, out S> {

    data class Fail<out F> internal constructor(val value: F) : Either<F, Nothing>() {
        companion object {
            operator fun <F> invoke(f: F): Either<F, Nothing> = Fail(f)
        }
    }

    data class Success<out S> internal constructor(val value: S) : Either<Nothing, S>() {
        companion object {
            operator fun <S> invoke(s: S): Either<Nothing, S> = Success(s)
        }
    }

    companion object {
        fun <S> success(value: S): Either<Nothing, S> = Success(value)
        fun <F> fail(value: F): Either<F, Nothing> = Fail(value)
    }
}
