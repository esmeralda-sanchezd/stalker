package com.example.stalker.api.models


import com.google.gson.annotations.SerializedName

data class Timezone(
    @SerializedName("description")
    val description: String,
    @SerializedName("offset")
    val offset: String
)