package com.example.dragonballapp_cikicmilos.presentation.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.repository.CharacterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CharacterListUiState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1,
    val hasNextPage: Boolean = true,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchResults: List<Character>? = null
)

class CharacterListViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            characterRepository.getCharacters(1).fold(
                onSuccess = { (characters, pagination) ->
                    _uiState.value = _uiState.value.copy(
                        characters = characters,
                        currentPage = pagination.currentPage,
                        hasNextPage = pagination.hasNextPage,
                        isLoading = false
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message ?: "Unknown error"
                    )
                }
            )
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true, error = null)
            characterRepository.getCharacters(1).fold(
                onSuccess = { (characters, pagination) ->
                    _uiState.value = _uiState.value.copy(
                        characters = characters,
                        currentPage = pagination.currentPage,
                        hasNextPage = pagination.hasNextPage,
                        isRefreshing = false,
                        searchQuery = "",
                        searchResults = null
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isRefreshing = false,
                        error = error.message ?: "Unknown error"
                    )
                }
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        searchJob?.cancel()

        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(searchResults = null, isSearching = false)
            return
        }

        searchJob = viewModelScope.launch {
            delay(300)
            _uiState.value = _uiState.value.copy(isSearching = true)
            characterRepository.searchCharacters(query).fold(
                onSuccess = { results ->
                    _uiState.value = _uiState.value.copy(
                        searchResults = results,
                        isSearching = false
                    )
                },
                onFailure = {
                    _uiState.value = _uiState.value.copy(
                        searchResults = emptyList(),
                        isSearching = false
                    )
                }
            )
        }
    }

    fun loadMore() {
        val state = _uiState.value
        if (state.isLoadingMore || !state.hasNextPage || state.searchResults != null) return

        viewModelScope.launch {
            _uiState.value = state.copy(isLoadingMore = true)
            val nextPage = state.currentPage + 1
            characterRepository.getCharacters(nextPage).fold(
                onSuccess = { (characters, pagination) ->
                    _uiState.value = _uiState.value.copy(
                        characters = _uiState.value.characters + characters,
                        currentPage = pagination.currentPage,
                        hasNextPage = pagination.hasNextPage,
                        isLoadingMore = false
                    )
                },
                onFailure = {
                    _uiState.value = _uiState.value.copy(isLoadingMore = false)
                }
            )
        }
    }
}
