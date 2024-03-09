package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class UserLoginEntity(
    @SerializedName("login")
    val login: String?,
    @SerializedName("password")
    val password: String?,
)
