package com.vito.cornelius.feature.registration.autologin.domain.model

class AutoLoginFailureException(
        message: String? = null,
        cause: Throwable? = null
) : Exception(message, cause)
