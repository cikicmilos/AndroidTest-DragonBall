package com.example.dragonballapp_cikicmilos.domain.repository

import com.example.dragonballapp_cikicmilos.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Character>>
    suspend fun toggleFavorite(characterId: Int)
    suspend fun isFavorite(characterId: Int): Boolean
}
