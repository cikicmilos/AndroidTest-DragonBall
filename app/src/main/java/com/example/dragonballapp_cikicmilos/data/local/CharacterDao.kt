package com.example.dragonballapp_cikicmilos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Query("SELECT * FROM characters WHERE cachedPage = :page ORDER BY id ASC")
    suspend fun getCharactersByPage(page: Int): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity?

    @Query("SELECT * FROM characters WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavorites(): Flow<List<CharacterEntity>>

    @Query("UPDATE characters SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: Int, isFavorite: Boolean)

    @Query("SELECT isFavorite FROM characters WHERE id = :id")
    suspend fun isFavorite(id: Int): Boolean?

    @Query("SELECT * FROM characters ORDER BY id ASC")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    suspend fun searchByName(name: String): List<CharacterEntity>
}
