package com.vito.cornelius.domain.common.model

sealed class ErrorCause {

    object NetworkNotAvailable : ErrorCause()

    data class ServerError(
            val errorCode: Int,
            val key: String,
            val message: String
    ) : ErrorCause()

    abstract class FeatureSpecificErrorCause : ErrorCause()
}