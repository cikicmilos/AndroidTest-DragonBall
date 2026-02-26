package com.example.dragonballapp_cikicmilos.domain.model

data class PaginationInfo(
    val totalItems: Int,
    val totalPages: Int,
    val currentPage: Int,
    val hasNextPage: Boolean
)
