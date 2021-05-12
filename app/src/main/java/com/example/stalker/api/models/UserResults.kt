package com.example.stalker.api.models


import com.google.gson.annotations.SerializedName

data class UserResults(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<User>
)