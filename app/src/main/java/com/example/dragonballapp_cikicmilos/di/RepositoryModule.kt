package com.example.dragonballapp_cikicmilos.di

import com.example.dragonballapp_cikicmilos.data.repository.CharacterRepositoryImpl
import com.example.dragonballapp_cikicmilos.data.repository.FavoritesRepositoryImpl
import com.example.dragonballapp_cikicmilos.domain.repository.CharacterRepository
import com.example.dragonballapp_cikicmilos.domain.repository.FavoritesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get()) }

    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
}
