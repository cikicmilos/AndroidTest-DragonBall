package com.example.dragonballapp_cikicmilos.data.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val characterDao: CharacterDao,
    private val transformationDao: TransformationDao
) {
    suspend fun insertCharacters(characters: List<CharacterEntity>) =
        characterDao.insertAll(characters)

    suspend fun insertCharacter(character: CharacterEntity) =
        characterDao.insert(character)

    suspend fun getCharactersByPage(page: Int) =
        characterDao.getCharactersByPage(page)

    suspend fun getCharacterById(id: Int) =
        characterDao.getCharacterById(id)

    fun getFavorites(): Flow<List<CharacterEntity>> =
        characterDao.getFavorites()

    suspend fun setFavorite(id: Int, isFavorite: Boolean) =
        characterDao.setFavorite(id, isFavorite)

    suspend fun isFavorite(id: Int): Boolean =
        characterDao.isFavorite(id) ?: false

    suspend fun getAllCharacters() =
        characterDao.getAllCharacters()

    suspend fun searchByName(name: String) =
        characterDao.searchByName(name)

    suspend fun insertTransformations(transformations: List<TransformationEntity>) =
        transformationDao.insertAll(transformations)

    suspend fun getTransformationsForCharacter(characterId: Int) =
        transformationDao.getTransformationsForCharacter(characterId)

    suspend fun deleteTransformationsForCharacter(characterId: Int) =
        transformationDao.deleteForCharacter(characterId)
}
