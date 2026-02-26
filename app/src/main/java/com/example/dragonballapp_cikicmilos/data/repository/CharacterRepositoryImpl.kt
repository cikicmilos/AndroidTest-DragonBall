package com.example.dragonballapp_cikicmilos.data.repository

import com.example.dragonballapp_cikicmilos.data.NetworkConnectivityHelper
import com.example.dragonballapp_cikicmilos.data.local.LocalDataSource
import com.example.dragonballapp_cikicmilos.data.remote.RemoteDataSource
import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.model.CharacterDetail
import com.example.dragonballapp_cikicmilos.domain.model.PaginationInfo
import com.example.dragonballapp_cikicmilos.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val connectivityHelper: NetworkConnectivityHelper
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Result<Pair<List<Character>, PaginationInfo>> {
        if (!connectivityHelper.isNetworkAvailable()) {
            return getCharactersFromCache(page)
        }

        return try {
            val response = remoteDataSource.getCharacters(page)
            val entities = response.items.map { it.toEntity(page) }

            entities.forEach { entity ->
                val isFav = localDataSource.isFavorite(entity.id)
                localDataSource.insertCharacter(entity.copy(isFavorite = isFav))
            }

            val characters = response.items.map { dto ->
                val isFav = localDataSource.isFavorite(dto.id)
                dto.toDomain().copy(isFavorite = isFav)
            }
            val pagination = response.meta.toDomain()
            Result.success(Pair(characters, pagination))
        } catch (e: Exception) {
            getCharactersFromCache(page)
        }
    }

    private suspend fun getCharactersFromCache(page: Int): Result<Pair<List<Character>, PaginationInfo>> {
        val cached = localDataSource.getCharactersByPage(page)
        return if (cached.isNotEmpty()) {
            val characters = cached.map { it.toDomain() }
            val pagination = PaginationInfo(
                totalItems = characters.size,
                totalPages = page,
                currentPage = page,
                hasNextPage = false
            )
            Result.success(Pair(characters, pagination))
        } else {
            Result.failure(Exception("No internet connection and no cached data available"))
        }
    }

    override suspend fun getCharacterDetail(id: Int): Result<CharacterDetail> {
        if (!connectivityHelper.isNetworkAvailable()) {
            return getCharacterDetailFromCache(id)
        }

        return try {
            val dto = remoteDataSource.getCharacterById(id)
            val isFav = localDataSource.isFavorite(id)

            localDataSource.insertCharacter(dto.toEntity(isFav))
            localDataSource.deleteTransformationsForCharacter(id)
            localDataSource.insertTransformations(dto.transformations.map { it.toEntity(id) })

            Result.success(dto.toDomain(isFav))
        } catch (e: Exception) {
            getCharacterDetailFromCache(id)
        }
    }

    private suspend fun getCharacterDetailFromCache(id: Int): Result<CharacterDetail> {
        val cached = localDataSource.getCharacterById(id)
        return if (cached != null) {
            val transformations = localDataSource.getTransformationsForCharacter(id)
            Result.success(cached.toDetailDomain(transformations))
        } else {
            Result.failure(Exception("No internet connection and character not cached"))
        }
    }

    override suspend fun searchCharacters(name: String): Result<List<Character>> {
        if (!connectivityHelper.isNetworkAvailable()) {
            return searchCharactersFromCache(name)
        }

        return try {
            val results = remoteDataSource.searchCharacters(name)
            Result.success(results.map { it.toCharacterDomain() })
        } catch (e: Exception) {
            searchCharactersFromCache(name)
        }
    }

    private suspend fun searchCharactersFromCache(name: String): Result<List<Character>> {
        val cached = localDataSource.searchByName(name)
        return if (cached.isNotEmpty()) {
            Result.success(cached.map { it.toDomain() })
        } else {
            Result.failure(Exception("No internet connection and no cached results"))
        }
    }

    override suspend fun getAllCachedCharacters(): List<Character> {
        return localDataSource.getAllCharacters().map { it.toDomain() }
    }
}
