package com.example.app.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRANSACTION_TABLE")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val dateTime: String?,
    val amount: Long,
    val description: String,
    val amountType: String,
    val type: String
)