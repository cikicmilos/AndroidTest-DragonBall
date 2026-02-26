package com.example.dragonballapp_cikicmilos.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(
    favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<Character>> = favoritesRepository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
