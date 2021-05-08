package com.example.randomdogimages.network


import com.google.gson.annotations.SerializedName

data class DogImatge(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)