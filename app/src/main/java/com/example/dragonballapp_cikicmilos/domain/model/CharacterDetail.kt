package com.example.dragonballapp_cikicmilos.domain.model

data class CharacterDetail(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val originPlanet: Planet?,
    val transformations: List<Transformation>,
    val isFavorite: Boolean = false
)
