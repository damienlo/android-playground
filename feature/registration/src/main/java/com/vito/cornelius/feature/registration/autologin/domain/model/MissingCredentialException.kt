package com.vito.cornelius.feature.registration.autologin.domain.model

class MissingCredentialException(
        message: String? = null,
        cause: Throwable? = null
) : Exception(message, cause)
