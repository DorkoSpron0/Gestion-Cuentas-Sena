package com.example.app.repository

import com.example.app.data.TransactionDao
import com.example.app.model.local.TransactionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryLocal @Inject constructor(
    private val dao: TransactionDao
){
    val allTransactions: Flow<List<TransactionEntity>> = dao.getAll()

    fun getById(id: Long): Flow<TransactionEntity?> {
        return dao.getById(id)
    }
    suspend fun insert(transaction: TransactionEntity){
        dao.insert(transaction)
    }

    suspend fun delete(transaction: TransactionEntity){
        dao.delete(transaction)
    }
}