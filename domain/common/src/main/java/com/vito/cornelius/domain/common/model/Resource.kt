package com.vito.cornelius.domain.common.model

// A generic class that contains data and status about loading this data.
sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val error: ErrorCause, val data: T? = null) : Resource<Nothing>()
    data class Loading<T : Any>(var data: T? = null) : Resource<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error<*> -> "Failure[failure=$error, data=$data]"
            is Loading -> "Loading[data=$data]"
        }
    }
}
