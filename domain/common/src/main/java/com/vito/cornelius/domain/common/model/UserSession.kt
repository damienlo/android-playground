package com.vito.cornelius.domain.common.model

data class UserSession(
        val userId: String,
        val authenticationToken: AuthenticationToken
)
