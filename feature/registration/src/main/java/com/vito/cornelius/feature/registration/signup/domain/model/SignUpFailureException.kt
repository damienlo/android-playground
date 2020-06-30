package com.vito.cornelius.feature.registration.signup.domain.model

class SignUpFailureException(
        message: String? = null,
        cause: Throwable? = null
) : Exception(message, cause)
