package com.vito.cornelius.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthenticationToken(
        @field:Json(name = "createdAt")
        val createdAt: Long,
        @field:Json(name = "token")
        val token: String,
        @field:Json(name = "expiresIn")
        val expiresIn: Long
)