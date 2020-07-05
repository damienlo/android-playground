package com.vito.cornelius.data.network.model

import com.vito.cornelius.data.network.model.NetworkResponse.Failure
import com.vito.cornelius.data.network.model.NetworkResponse.Success
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.AT_MOST_ONCE
import kotlin.contracts.contract

sealed class NetworkResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResponse<T>()
    data class Failure(val error: ApiError) : NetworkResponse<Nothing>()
}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> NetworkResponse<T>.onFailure(
        action: (error: ApiError) -> Unit
): NetworkResponse<T> {
    contract {
        callsInPlace(action, AT_MOST_ONCE)
    }
    if (this is Failure) action(error)
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T : Any> NetworkResponse<T>.onSuccess(
        action: (value: T) -> Unit
): NetworkResponse<T> {
    contract {
        callsInPlace(action, AT_MOST_ONCE)
    }
    if (this is Success) action(data)
    return this
}