package com.example.dragonballapp_cikicmilos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val originPlanetName: String? = null,
    val originPlanetDescription: String? = null,
    val originPlanetImage: String? = null,
    val originPlanetIsDestroyed: Boolean? = null,
    val isFavorite: Boolean = false,
    val cachedPage: Int = 0
)
