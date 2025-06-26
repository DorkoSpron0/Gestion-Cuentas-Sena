package com.example.app.di

import android.content.Context
import androidx.room.Room
import com.example.app.data.TransactionDao
import com.example.app.data.TransactionDatabase
import com.example.app.remote.TransactionApi
import com.example.app.remote.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl(): String = "http://10.0.2.2:8081/"

    @Provides
    @Singleton
    fun providerRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providerUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun providerTransactionApi(retrofit: Retrofit): TransactionApi =
        retrofit.create(TransactionApi::class.java)

    // 👇 ESTA FUNCIÓN FALTABA
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TransactionDatabase {
        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            "TRANSACTION_DATABASE"
        ).build()
    }

    @Provides
    fun provideTransactionDao(db: TransactionDatabase): TransactionDao {
        return db.todoDao()
    }
}
