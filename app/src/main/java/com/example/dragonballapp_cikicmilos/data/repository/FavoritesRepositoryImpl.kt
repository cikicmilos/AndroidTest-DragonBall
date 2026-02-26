package com.example.dragonballapp_cikicmilos.data.repository

import com.example.dragonballapp_cikicmilos.data.local.LocalDataSource
import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val localDataSource: LocalDataSource
) : FavoritesRepository {

    override fun getFavorites(): Flow<List<Character>> {
        return localDataSource.getFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(characterId: Int) {
        val currentFav = localDataSource.isFavorite(characterId)
        localDataSource.setFavorite(characterId, !currentFav)
    }

    override suspend fun isFavorite(characterId: Int): Boolean {
        return localDataSource.isFavorite(characterId)
    }
}
