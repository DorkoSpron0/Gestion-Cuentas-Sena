package com.example.app.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionModelApi(
    val id: Long?,
    val dateTime: String?,
    val amount: Long,
    val description: String,
    val amountType: String,
    val type: String
)