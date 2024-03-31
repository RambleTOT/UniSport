package com.example.unisportinverse.data.util

import com.example.unisportinverse.data.model.GetTokenResponse
import com.example.unisportinverse.data.model.UserLoginEntity
import com.example.unisportinverse.data.model.UserRegisterEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiMethod {

    @POST("auth/login")
    fun loginUser(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

    @POST("auth/register")
    fun registerUser(
        @Body body: UserRegisterEntity
    ): Call<GetTokenResponse>


}