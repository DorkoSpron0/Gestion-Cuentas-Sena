package com.example.app.remote

import com.example.app.model.api.UserModelApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("users/register")
    suspend fun registerUser(@Body userModel: UserModelApi): Response<UserModelApi>;

    @POST("users/login")
    suspend fun loginUser(@Body userModel: UserModelApi): Response<UserModelApi>;
}