package com.sixseven.godsunmade.ui.leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.data.repository.LeaderboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** Estados del ranking: Loading, Success con podio (3 primeros) y resto, o Error. */
sealed interface LeaderboardUiState {
    data object Loading : LeaderboardUiState
    data class Success(val podio: List<Player>, val resto: List<Player>) : LeaderboardUiState
    data class Error(val mensaje: String) : LeaderboardUiState
}

/** ViewModel del ranking: carga los jugadores y separa el podio del resto. */
class LeaderboardViewModel(private val repository: LeaderboardRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<LeaderboardUiState>(LeaderboardUiState.Loading)
    val uiState: StateFlow<LeaderboardUiState> = _uiState

    init { cargar() }

    // Pasa a Loading y luego a Success o Error; runCatching captura los fallos del repositorio.
    fun cargar() {
        _uiState.value = LeaderboardUiState.Loading
        viewModelScope.launch {
            _uiState.value = runCatching {
                val todos = repository.getPlayers()
                LeaderboardUiState.Success(todos.take(3), todos.drop(3))
            }.getOrElse { LeaderboardUiState.Error(it.message ?: "Error al cargar") }
        }
    }
}
