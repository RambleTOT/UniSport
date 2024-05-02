package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class GetEventsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("beginningAt")
    val beginningAt: String?,
    @SerializedName("endingAt")
    val endingAt: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("price")
    val price: String?,
)