package com.example.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.app.model.local.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TRANSACTION_TABLE")
    fun getAll(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM TRANSACTION_TABLE WHERE id = :id")
    fun getById(id: Long): Flow<TransactionEntity?>

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity)

    @Delete
    suspend fun delete(transaction: TransactionEntity)
}