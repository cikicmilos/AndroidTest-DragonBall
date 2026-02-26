package com.example.dragonballapp_cikicmilos.presentation.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballapp_cikicmilos.domain.model.CharacterDetail
import com.example.dragonballapp_cikicmilos.domain.repository.CharacterRepository
import com.example.dragonballapp_cikicmilos.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CharacterDetailUiState(
    val character: CharacterDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _uiState.value = CharacterDetailUiState(isLoading = true)
            characterRepository.getCharacterDetail(id).fold(
                onSuccess = { detail ->
                    _uiState.value = CharacterDetailUiState(character = detail)
                },
                onFailure = { error ->
                    _uiState.value = CharacterDetailUiState(
                        error = error.message ?: "Failed to load character"
                    )
                }
            )
        }
    }

    fun toggleFavorite() {
        val character = _uiState.value.character ?: return
        viewModelScope.launch {
            favoritesRepository.toggleFavorite(character.id)
            _uiState.value = _uiState.value.copy(
                character = character.copy(isFavorite = !character.isFavorite)
            )
        }
    }
}
