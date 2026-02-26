package com.example.dragonballapp_cikicmilos.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransformationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transformations: List<TransformationEntity>)

    @Query("SELECT * FROM transformations WHERE characterId = :characterId ORDER BY id ASC")
    suspend fun getTransformationsForCharacter(characterId: Int): List<TransformationEntity>

    @Query("DELETE FROM transformations WHERE characterId = :characterId")
    suspend fun deleteForCharacter(characterId: Int)
}
