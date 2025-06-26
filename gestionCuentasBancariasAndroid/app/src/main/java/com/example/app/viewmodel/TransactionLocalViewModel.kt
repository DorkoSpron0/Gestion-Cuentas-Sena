package com.example.app.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.model.local.TransactionEntity
import com.example.app.repository.TransactionRepositoryLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TransactionLocalViewModel @Inject constructor(
    private val repositoryLocal: TransactionRepositoryLocal
): ViewModel() {

    // -> LOCAL
    val transactionsLocal = repositoryLocal.allTransactions.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // -> API
    private val _transactionLocal = MutableStateFlow<TransactionEntity?>(null)
    val transactionLocal: StateFlow<TransactionEntity?> = _transactionLocal

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getTransactionById(transactionId: Long){
        viewModelScope.launch {
            repositoryLocal.getById(transactionId).collect { transaction ->
                _transactionLocal.value = transaction
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createTransaction(amount: Long, description: String, amountType: String, type: String){
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

                val newLocalTransaction = TransactionEntity(
                    dateTime = LocalDateTime.now().toString(),
                    amount = amount,
                    description = description,
                    amountType = amountType,
                    type = type
                )

                repositoryLocal.insert(newLocalTransaction)
            }catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTransaction(transactionId: Long, amount: Long, description: String, amountType: String, type: String){
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

                val newTransaction = TransactionEntity(
                    transactionId.toInt(),
                    LocalDateTime.now().toString(),
                    amount,
                    description,
                    amountType,
                    type
                );

                repositoryLocal.insert(newTransaction)
            }catch (e: Exception) {
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }


    fun deleteTransaction(transaction: TransactionEntity){
        viewModelScope.launch {
            try{
                repositoryLocal.delete(transaction)
            }catch (e: Exception){
                _error.value = "Excepción: ${e.localizedMessage ?: "Error desconocido"}"
            }
        }
    }
}