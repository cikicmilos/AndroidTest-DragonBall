package com.example.dragonballapp_cikicmilos.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transformations",
    foreignKeys = [
        ForeignKey(
            entity = CharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("characterId")]
)
data class TransformationEntity(
    @PrimaryKey val id: Int,
    val characterId: Int,
    val name: String,
    val image: String,
    val ki: String
)
