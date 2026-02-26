package com.example.dragonballapp_cikicmilos.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TransformationDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("ki") val ki: String
)
