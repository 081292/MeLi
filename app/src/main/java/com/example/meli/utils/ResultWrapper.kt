package com.example.meli.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val message: String? = null): ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    val code = null
                    val errorMessage = throwable.message
                    ResultWrapper.GenericError(code, errorMessage)
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorMessage = throwable.message()
                    ResultWrapper.GenericError(code, errorMessage)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}