package com.example.stalker.api.models


import com.google.gson.annotations.SerializedName

data class Dob(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)