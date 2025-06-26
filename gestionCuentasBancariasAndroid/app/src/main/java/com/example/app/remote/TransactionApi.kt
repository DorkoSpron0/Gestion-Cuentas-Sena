package com.example.app.remote

import com.example.app.model.api.TransactionModelApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TransactionApi {
    @GET("transaction/{userId}")
    suspend fun fetchTransactions(@Path("userId") userId: Long): Response<List<TransactionModelApi>>

    @GET("transaction/transactions/{transactionId}")
    suspend fun getTransactionById(@Path("transactionId") transactionId: Long): Response<TransactionModelApi>

    @POST("transaction/{userId}")
    suspend fun addTransaction(@Path("userId") userId: Long, @Body transactionModelApi: TransactionModelApi): Response<TransactionModelApi>

    @PUT("transaction/{transactionId}")
    suspend fun updateTransaction(@Path("transactionId") transactionId: Long, @Body transactionModelApi: TransactionModelApi): Response<TransactionModelApi>

    @DELETE("transaction/{transactionId}")
    suspend fun deleteTransaction(@Path("transactionId") transactionId: Long): Response<Unit>
}