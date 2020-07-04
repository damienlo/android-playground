package com.vito.cornelius.data.authentication.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLoginBodyRequest(
        @field:Json(name = "email")
        val email: String,
        @field:Json(name = "password")
        val password: String
)