package com.example.app.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserModelApi (
    val id: Long = 0,
    val email: String,
    val password: String)