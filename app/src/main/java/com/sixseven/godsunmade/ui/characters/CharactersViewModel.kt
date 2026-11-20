package com.sixseven.godsunmade.ui.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface CharactersUiState {
    data object Loading : CharactersUiState
    data class Success(val principal: Character?, val enemigos: List<Character>) : CharactersUiState
    data class Error(val mensaje: String) : CharactersUiState
}

sealed interface CharacterDetailUiState {
    data object Loading : CharacterDetailUiState
    data class Success(val personaje: Character) : CharacterDetailUiState
    data class Error(val mensaje: String) : CharacterDetailUiState
}

class CharactersViewModel(private val repository: CharactersRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState: StateFlow<CharactersUiState> = _uiState

    init { cargar() }

    fun cargar() {
        _uiState.value = CharactersUiState.Loading
        viewModelScope.launch {
            _uiState.value = runCatching {
                val todos = repository.getCharacters().sortedBy { it.registro }
                CharactersUiState.Success(todos.firstOrNull { it.esPrincipal }, todos.filterNot { it.esPrincipal })
            }.getOrElse { CharactersUiState.Error(it.message ?: "Error al cargar") }
        }
    }
}

class CharacterDetailViewModel(
    private val repository: CharactersRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val id: String = checkNotNull(savedStateHandle["id"])
    private val _uiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val uiState: StateFlow<CharacterDetailUiState> = _uiState

    init { cargar() }

    fun cargar() {
        _uiState.value = CharacterDetailUiState.Loading
        viewModelScope.launch {
            _uiState.value = runCatching {
                CharacterDetailUiState.Success(repository.getCharacter(id) ?: error("Personaje no encontrado"))
            }.getOrElse { CharacterDetailUiState.Error(it.message ?: "Error al cargar") }
        }
    }
}
