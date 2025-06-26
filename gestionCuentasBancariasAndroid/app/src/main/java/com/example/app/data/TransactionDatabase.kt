package com.example.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app.model.local.TransactionEntity


@Database(entities = [TransactionEntity::class], version = 1) // Cambiar con el minimo cambio
abstract class TransactionDatabase : RoomDatabase(){
    abstract fun todoDao(): TransactionDao

    companion object {
        @Volatile // Ese elemento cambia mediante la configuración
        private var INSTANCE: TransactionDatabase? = null

        fun getDatabase(context: Context): TransactionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "TRANSACTION_DATABASE"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}