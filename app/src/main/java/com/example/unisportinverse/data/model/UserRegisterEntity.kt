package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class UserRegisterEntity(
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("role")
    val role: String?,
)
