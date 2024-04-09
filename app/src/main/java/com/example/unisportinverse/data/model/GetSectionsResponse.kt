package com.example.unisportinverse.data.model

import com.google.gson.annotations.SerializedName

data class GetSectionsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("category")
    val category: CategorySections?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("timetable")
    val timetable: String?,
)