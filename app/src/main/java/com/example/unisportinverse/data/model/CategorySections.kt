package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class CategorySections(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
)
