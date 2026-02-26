package com.example.dragonballapp_cikicmilos.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlanetDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("isDestroyed") val isDestroyed: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String
)
