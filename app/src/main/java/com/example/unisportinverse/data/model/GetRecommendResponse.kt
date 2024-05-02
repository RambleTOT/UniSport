package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class GetRecommendResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("beginningAt")
    val beginningAt: String?,
    @SerializedName("endingAt")
    val endingAt: String?,
    @SerializedName("price")
    val price: String?,
)