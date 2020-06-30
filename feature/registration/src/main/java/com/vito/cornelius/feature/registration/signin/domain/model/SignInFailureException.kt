package com.vito.cornelius.feature.registration.signin.domain.model

class SignInFailureException(
        message: String? = null,
        cause: Throwable? = null
) : Exception(message, cause)
