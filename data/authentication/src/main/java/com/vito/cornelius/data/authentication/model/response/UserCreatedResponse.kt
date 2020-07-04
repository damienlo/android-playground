package com.vito.cornelius.data.authentication.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserCreatedResponse(
        @field:Json(name = "user")
        val userId: String,
        @field:Json(name = "token")
        val token: AuthenticationTokenResponse
)
