package com.example.dragonballapp_cikicmilos.data.remote

import com.example.dragonballapp_cikicmilos.data.remote.dto.CharacterDetailDto
import com.example.dragonballapp_cikicmilos.data.remote.dto.CharacterListResponseDto

class RemoteDataSource(private val api: DragonBallApiService) {

    suspend fun getCharacters(page: Int, limit: Int = 10): CharacterListResponseDto {
        return api.getCharacters(page, limit)
    }

    suspend fun getCharacterById(id: Int): CharacterDetailDto {
        return api.getCharacterById(id)
    }

    suspend fun searchCharacters(name: String): List<CharacterDetailDto> {
        return api.searchCharacters(name)
    }
}
