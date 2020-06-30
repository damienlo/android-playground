package com.vito.cornelius.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimpleUser(
        @field:Json(name = "_id")
        val id: String,
        @field:Json(name = "user_name")
        val userName: String
)