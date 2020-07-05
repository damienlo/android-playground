package com.vito.cornelius.data.network.model

sealed class ApiError {
    object NoNetwork : ApiError()
    object Timeout : ApiError()
    data class Server(
            val errorCode: Int,
            val body: String?
    ) : ApiError()
}