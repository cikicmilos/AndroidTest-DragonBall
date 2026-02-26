package com.example.dragonballapp_cikicmilos.domain.repository

import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.model.CharacterDetail
import com.example.dragonballapp_cikicmilos.domain.model.PaginationInfo

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<Pair<List<Character>, PaginationInfo>>
    suspend fun getCharacterDetail(id: Int): Result<CharacterDetail>
    suspend fun searchCharacters(name: String): Result<List<Character>>
    suspend fun getAllCachedCharacters(): List<Character>
}
