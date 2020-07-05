package com.vito.cornelius.domain.common.model.authentication

import com.vito.cornelius.domain.common.model.authentication.AuthenticationToken

data class UserSession(
        val userId: String,
        val authenticationToken: AuthenticationToken
)
