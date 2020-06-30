package com.vito.cornelius.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserSignUpInfo(
        @field:Json(name = "name")
        val name: String,
        @field:Json(name = "email")
        val email: String,
        @field:Json(name = "password")
        val password: String
)