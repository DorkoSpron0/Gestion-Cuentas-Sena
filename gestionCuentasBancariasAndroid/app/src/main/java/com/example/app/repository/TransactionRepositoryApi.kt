package com.example.app.repository

import com.example.app.model.api.TransactionModelApi
import com.example.app.remote.TransactionApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import javax.inject.Inject

class TransactionRepositoryApi @Inject constructor(
    private val api: TransactionApi
){
    suspend fun fetchTransactions(@Path("userId") userId: Long): Response<List<TransactionModelApi>> {
        return  api.fetchTransactions(userId)
    }

    suspend fun getTransactionById(@Path("transactionId") transactionId: Long): Response<TransactionModelApi> {
        return api.getTransactionById(transactionId)
    }

    suspend fun addTransaction(@Path("userId") userId: Long, @Body transactionModelApi: TransactionModelApi): Response<TransactionModelApi> {
        return api.addTransaction(userId, transactionModelApi)
    }
    suspend fun updateTransaction(@Path("transactionId") transactionId: Long, @Body transactionModelApi: TransactionModelApi): Response<TransactionModelApi> {
        return api.updateTransaction(transactionId, transactionModelApi)
    }
    suspend fun deleteTransaction(@Path("transactionId") transactionId: Long): Response<Unit> {
        return api.deleteTransaction(transactionId)
    }
}