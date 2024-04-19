package com.example.unisportinverse.data.util

import com.example.unisportinverse.data.model.GetGroundsResponse
import com.example.unisportinverse.data.model.GetMyAccount
import com.example.unisportinverse.data.model.GetSectionsResponse
import com.example.unisportinverse.data.model.GetTokenResponse
import com.example.unisportinverse.data.model.UserLoginEntity
import com.example.unisportinverse.data.model.UserRegisterEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiMethod {

    @POST("auth/login")
    fun loginUser(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

    @POST("auth/register")
    fun registerUser(
        @Body body: UserRegisterEntity
    ): Call<GetTokenResponse>

    @GET("sections")
    fun getSections(
    ): Call<List<GetSectionsResponse>>

    @GET("users/me")
    fun getMyAccount(
        @Header("Authorization") token: String
    ): Call<GetMyAccount>

    @GET("grounds")
    fun getGrounds(
    ): Call<List<GetGroundsResponse>>

}