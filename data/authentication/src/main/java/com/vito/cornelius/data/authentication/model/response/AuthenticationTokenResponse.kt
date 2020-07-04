package com.vito.cornelius.data.authentication.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationTokenResponse(
        @field:Json(name = "createdAt")
        val createdAt: Long,
        @field:Json(name = "token")
        val token: String,
        @field:Json(name = "expiresIn")
        val expiresIn: Long
)