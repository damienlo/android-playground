package com.vito.cornelius.data.network

import com.vito.cornelius.data.network.model.ApiError
import com.vito.cornelius.data.network.model.NetworkResponse
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend inline fun <T : Any> safeApiCall(
        crossinline call: suspend () -> T
): NetworkResponse<T> = try {
    val data = call.invoke()
    NetworkResponse.Success(data)
} catch (e: Exception) {
    NetworkResponse.Failure(e.toApiError())
}

fun HttpException.toServerApiError(): ApiError.Server {
    val error = response()?.errorBody()
    val responseCode = response()?.code() ?: -1
    val errorBody = when {
        error == null -> null // No error content available
        error.contentLength() == 0L -> null // Error content is empty
        else -> try {
            error.toString()
        } catch (e: Exception) {
            // If unable to extract content, return with a null body and don't parse further
            return ApiError.Server(responseCode, null)
        }
    }
    return ApiError.Server(responseCode, errorBody)
}

fun Throwable.toApiError(): ApiError {
    return when (this) {
        is IOException -> ApiError.NoNetwork
        is SocketTimeoutException -> ApiError.Timeout
        is HttpException -> toServerApiError()
        else -> ApiError.Server(-1, null)
    }
}
