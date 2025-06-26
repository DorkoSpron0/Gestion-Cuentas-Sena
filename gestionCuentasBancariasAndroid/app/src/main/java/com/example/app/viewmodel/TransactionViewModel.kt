package com.example.app.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.model.api.TransactionModelApi
import com.example.app.model.local.TransactionEntity
import com.example.app.repository.TransactionRepositoryApi
import com.example.app.repository.TransactionRepositoryLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepositoryApi,
    private val localRepository: TransactionRepositoryLocal
): ViewModel() {

    // -> LOCAL
    val transactionsLocal = localRepository.allTransactions.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // -> API
    private val _transactions = MutableStateFlow<List<TransactionModelApi>>(emptyList())
    val transactions: StateFlow<List<TransactionModelApi>> = _transactions.asStateFlow()

    private val _transaction = MutableStateFlow<TransactionModelApi?>(null)
    val transaction: StateFlow<TransactionModelApi?> = _transaction.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchTransactions(userId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.fetchTransactions(userId)
                if (response.isSuccessful) {
                    _transactions.value = response.body() ?: emptyList()
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }

    fun getTransactionById(transactionId: Long){
        viewModelScope.launch {
            try {
                val response = repository.getTransactionById(transactionId)
                if (response.isSuccessful) {
                    _transaction.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            }catch (e: Exception){
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createTransaction(userId: Long, amount: Long, description: String, amountType: String, type: String){
        viewModelScope.launch {
            try{

                var amountType = amountType
                var type = type

                if(amountType == "Ingreso"){
                    amountType = "INCOME"
                }else{
                    amountType = "EXPENSE"
                }

                if(type == "Bancaria"){
                    type = "BANK"
                }else if(type == "Efectivo"){
                    type = "CASH"
                }else if(type == "Virtual"){
                    type = "VIRTUAL"
                }else{
                    type = "QR"
                }

                val newTransaction = TransactionModelApi(
                    null,
                    null,
                    amount,
                    description,
                    amountType,
                    type
                );

                val newLocalTransaction = TransactionEntity(
                    dateTime = LocalDateTime.now().toString(),
                    amount = amount,
                    description = description,
                    amountType = amountType,
                    type = type
                )

                // -> PRIMERO SE GUARDA EN LOCAL Y LUEGO LO INTENTA EN LA API
                localRepository.insert(newLocalTransaction)

                val response = repository.addTransaction(userId, newTransaction)

                fetchTransactions(userId)

                if(!response.isSuccessful){
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            }catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }

    fun updateTransaction(userId: Long, transactionId: Long, amount: Long, description: String, amountType: String, type: String){
        viewModelScope.launch {
            try{
                var amountType = amountType
                var type = type

                if(amountType == "Ingreso"){
                    amountType = "INCOME"
                }else{
                    amountType = "EXPENSE"
                }

                if(type == "Bancaria"){
                    type = "BANK"
                }else if(type == "Efectivo"){
                    type = "CASH"
                }else if(type == "Virtual"){
                    type = "VIRTUAL"
                }else{
                    type = "QR"
                }

                val newTransaction = TransactionModelApi(
                    transactionId,
                    null,
                    amount,
                    description,
                    amountType,
                    type
                );

                val response = repository.updateTransaction(transactionId, newTransaction)

                if(response.isSuccessful){
                    fetchTransactions(userId)
                }
            }catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }

    fun deleteTransaction(transactionId: Long){
        viewModelScope.launch {
            try{
               val response = repository.deleteTransaction(transactionId)
                if(!response.isSuccessful){
                    _error.value = "Error: ${response.code()} - ${response.message()}"
                }
            }catch (e: Exception){
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }
}