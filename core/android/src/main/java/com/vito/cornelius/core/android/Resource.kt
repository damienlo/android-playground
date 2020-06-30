package com.vito.cornelius.core.android

// A generic class that contains data and status about loading this data.
sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val cause: Throwable) : Resource<Nothing>()
    data class Loading<T : Any>(var data: T? = null) : Resource<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Failure[failure=$cause]"
            is Loading -> "Loading[data=$data]"
        }
    }
}