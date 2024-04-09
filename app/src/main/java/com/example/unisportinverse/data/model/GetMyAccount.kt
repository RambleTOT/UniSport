package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class GetMyAccount(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("children")
    val children: List<String>?,
    @SerializedName("parents")
    val parents: List<String>?,

)
