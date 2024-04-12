package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class GetGroundsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("timetable")
    val timetable: String?,
)