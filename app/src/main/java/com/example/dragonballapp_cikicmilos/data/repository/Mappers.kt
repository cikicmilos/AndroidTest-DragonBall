package com.example.dragonballapp_cikicmilos.data.repository

import com.example.dragonballapp_cikicmilos.data.local.CharacterEntity
import com.example.dragonballapp_cikicmilos.data.local.TransformationEntity
import com.example.dragonballapp_cikicmilos.data.remote.dto.CharacterDetailDto
import com.example.dragonballapp_cikicmilos.data.remote.dto.CharacterDto
import com.example.dragonballapp_cikicmilos.data.remote.dto.PaginationMetaDto
import com.example.dragonballapp_cikicmilos.data.remote.dto.PlanetDto
import com.example.dragonballapp_cikicmilos.data.remote.dto.TransformationDto
import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.model.CharacterDetail
import com.example.dragonballapp_cikicmilos.domain.model.PaginationInfo
import com.example.dragonballapp_cikicmilos.domain.model.Planet
import com.example.dragonballapp_cikicmilos.domain.model.Transformation


fun CharacterDto.toDomain(): Character = Character(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation
)

fun CharacterDetailDto.toDomain(isFavorite: Boolean = false): CharacterDetail = CharacterDetail(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation,
    originPlanet = originPlanet?.toDomain(),
    transformations = transformations.map { it.toDomain() },
    isFavorite = isFavorite
)

fun CharacterDetailDto.toCharacterDomain(): Character = Character(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation
)

fun TransformationDto.toDomain(): Transformation = Transformation(
    id = id,
    name = name,
    image = image,
    ki = ki
)

fun PlanetDto.toDomain(): Planet = Planet(
    id = id,
    name = name,
    isDestroyed = isDestroyed,
    description = description,
    image = image
)

fun PaginationMetaDto.toDomain(): PaginationInfo = PaginationInfo(
    totalItems = totalItems,
    totalPages = totalPages,
    currentPage = currentPage,
    hasNextPage = currentPage < totalPages
)

fun CharacterDto.toEntity(page: Int): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation,
    cachedPage = page
)

fun CharacterDetailDto.toEntity(isFavorite: Boolean = false): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation,
    originPlanetName = originPlanet?.name,
    originPlanetDescription = originPlanet?.description,
    originPlanetImage = originPlanet?.image,
    originPlanetIsDestroyed = originPlanet?.isDestroyed,
    isFavorite = isFavorite
)

fun TransformationDto.toEntity(characterId: Int): TransformationEntity = TransformationEntity(
    id = id,
    characterId = characterId,
    name = name,
    image = image,
    ki = ki
)

fun CharacterEntity.toDomain(): Character = Character(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation,
    isFavorite = isFavorite
)

fun CharacterEntity.toDetailDomain(transformations: List<TransformationEntity>): CharacterDetail = CharacterDetail(
    id = id,
    name = name,
    ki = ki,
    maxKi = maxKi,
    race = race,
    gender = gender,
    description = description,
    image = image,
    affiliation = affiliation,
    originPlanet = if (originPlanetName != null) Planet(
        id = 0,
        name = originPlanetName,
        isDestroyed = originPlanetIsDestroyed ?: false,
        description = originPlanetDescription ?: "",
        image = originPlanetImage ?: ""
    ) else null,
    transformations = transformations.map { it.toDomain() },
    isFavorite = isFavorite
)

fun TransformationEntity.toDomain(): Transformation = Transformation(
    id = id,
    name = name,
    image = image,
    ki = ki
)
