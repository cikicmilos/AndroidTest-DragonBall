package com.example.dragonballapp_cikicmilos.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDetailDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ki") val ki: String,
    @SerializedName("maxKi") val maxKi: String,
    @SerializedName("race") val race: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("affiliation") val affiliation: String,
    @SerializedName("originPlanet") val originPlanet: PlanetDto?,
    @SerializedName("transformations") val transformations: List<TransformationDto>
)
