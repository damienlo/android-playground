package com.vito.cornelius.data.session.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SerializedSession (
        @field:Json(name = "user_id")
        val userId: String,
        @field:Json(name = "token_created_at")
        val createdAt: Long,
        @field:Json(name = "token_value")
        val token: String,
        @field:Json(name = "token_expires_in")
        val expiresIn: Long
)