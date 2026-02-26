package com.example.dragonballapp_cikicmilos.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterListResponseDto(
    @SerializedName("items") val items: List<CharacterDto>,
    @SerializedName("meta") val meta: PaginationMetaDto,
    @SerializedName("links") val links: LinksDto
)

data class PaginationMetaDto(
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("itemCount") val itemCount: Int,
    @SerializedName("itemsPerPage") val itemsPerPage: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("currentPage") val currentPage: Int
)

data class LinksDto(
    @SerializedName("first") val first: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("next") val next: String?,
    @SerializedName("last") val last: String?
)
