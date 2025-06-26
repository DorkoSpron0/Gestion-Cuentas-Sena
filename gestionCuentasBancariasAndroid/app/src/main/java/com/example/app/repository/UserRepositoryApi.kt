package com.example.app.repository

import com.example.app.model.api.UserModelApi
import com.example.app.remote.UserApi
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryApi @Inject constructor(
    private val api: UserApi
) {

    suspend fun registerUser(userModelApi: UserModelApi): Response<UserModelApi> {
        return api.registerUser(userModelApi)
    }

    suspend fun loginUser(userModelApi: UserModelApi): Response<UserModelApi> {
        return api.loginUser(userModelApi)
    }
}