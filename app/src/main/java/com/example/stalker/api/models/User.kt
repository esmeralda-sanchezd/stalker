package com.example.stalker.api.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Id,
    @SerializedName("name")
    val name: Name,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val picture: Picture
)