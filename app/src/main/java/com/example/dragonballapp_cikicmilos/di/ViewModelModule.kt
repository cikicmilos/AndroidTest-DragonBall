package com.example.dragonballapp_cikicmilos.di

import com.example.dragonballapp_cikicmilos.presentation.characterdetail.CharacterDetailViewModel
import com.example.dragonballapp_cikicmilos.presentation.characterlist.CharacterListViewModel
import com.example.dragonballapp_cikicmilos.presentation.favorites.FavoritesViewModel
import com.example.dragonballapp_cikicmilos.presentation.vscomparison.VsComparisonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { CharacterListViewModel(get()) }

    viewModel { CharacterDetailViewModel(get(), get()) }

    viewModel { FavoritesViewModel(get()) }

    viewModel { VsComparisonViewModel(get()) }
}
