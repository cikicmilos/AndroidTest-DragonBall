package com.example.dragonballapp_cikicmilos.data.remote

import com.example.dragonballapp_cikicmilos.data.remote.dto.CharacterDetailDto
import com.example.dragonballapp_cikicmilos.data.remote.dto.CharacterListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApiService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): CharacterListResponseDto

    @GET("characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDetailDto

    @GET("characters")
    suspend fun searchCharacters(
        @Query("name") name: String
    ): List<CharacterDetailDto>
}
