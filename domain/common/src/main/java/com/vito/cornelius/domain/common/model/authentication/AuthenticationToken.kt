package com.vito.cornelius.domain.common.model.authentication

data class AuthenticationToken(
        val createdAt: Long,
        val token: String,
        val expiresIn: Long
)