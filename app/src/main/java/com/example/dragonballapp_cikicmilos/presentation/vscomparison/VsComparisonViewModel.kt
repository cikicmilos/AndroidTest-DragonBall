package com.example.dragonballapp_cikicmilos.presentation.vscomparison

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonballapp_cikicmilos.domain.model.Character
import com.example.dragonballapp_cikicmilos.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class VsComparisonUiState(
    val allCharacters: List<Character> = emptyList(),
    val character1: Character? = null,
    val character2: Character? = null,
    val isLoading: Boolean = false,
    val winner: Winner = Winner.NONE
)

enum class Winner {
    NONE, CHARACTER1, CHARACTER2, TIE
}

class VsComparisonViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VsComparisonUiState())
    val uiState: StateFlow<VsComparisonUiState> = _uiState.asStateFlow()

    init {
        loadAllCharacters()
    }

    private fun loadAllCharacters() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val allChars = mutableListOf<Character>()
            var failed = false
            for (page in 1..5) {
                if (failed) break
                val result = characterRepository.getCharacters(page)
                result.onSuccess { (characters, _) ->
                    allChars.addAll(characters)
                }
                result.onFailure { failed = true }
            }
            if (allChars.isEmpty()) {
                // Fallback to cached
                allChars.addAll(characterRepository.getAllCachedCharacters())
            }
            _uiState.value = _uiState.value.copy(
                allCharacters = allChars.distinctBy { it.id },
                isLoading = false
            )
        }
    }

    fun selectCharacter1(character: Character) {
        _uiState.value = _uiState.value.copy(character1 = character)
        compareCharacters()
    }

    fun selectCharacter2(character: Character) {
        _uiState.value = _uiState.value.copy(character2 = character)
        compareCharacters()
    }

    private fun compareCharacters() {
        val c1 = _uiState.value.character1
        val c2 = _uiState.value.character2
        if (c1 == null || c2 == null) {
            _uiState.value = _uiState.value.copy(winner = Winner.NONE)
            return
        }
        val ki1 = parseKi(c1.maxKi)
        val ki2 = parseKi(c2.maxKi)
        val winner = when {
            ki1 > ki2 -> Winner.CHARACTER1
            ki2 > ki1 -> Winner.CHARACTER2
            else -> Winner.TIE
        }
        _uiState.value = _uiState.value.copy(winner = winner)
    }

    private fun parseKi(ki: String): Double {
        val trimmed = ki.trim()
        if (trimmed.equals("infinity", ignoreCase = true) ||
            trimmed.equals("googolplex", ignoreCase = true)) {
            return Double.MAX_VALUE
        }

        return try {
            val parts = trimmed.split(" ", limit = 2)

            val numberStr = parts[0].replace(",", "")
            val number = numberStr.toDoubleOrNull() ?: 0.0
            val multiplier = if (parts.size > 1) {
                when (parts[1].lowercase()) {
                    "thousand" -> 1e3
                    "million" -> 1e6
                    "billion" -> 1e9
                    "trillion" -> 1e12
                    "quadrillion" -> 1e15
                    "quintillion" -> 1e18
                    "sextillion" -> 1e21
                    "septillion" -> 1e24
                    "octillion" -> 1e27
                    "nonillion" -> 1e30
                    "decillion" -> 1e33
                    "googolplex" -> Double.MAX_VALUE
                    "infinity" -> Double.MAX_VALUE
                    else -> 1.0
                }
            } else 1.0
            number * multiplier
        } catch (e: Exception) {
            0.0
        }
    }
}
