package com.sixseven.godsunmade.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Estados de la pantalla Inicio: Loading, Success con el contenido o Error. */
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val fraseMaestra: String, val logline: String, val avisoLore: String) : HomeUiState
    data class Error(val mensaje: String) : HomeUiState
}

/** ViewModel de Inicio: expone el contenido estático como StateFlow de solo lectura. */
class HomeViewModel : ViewModel() {
    // Contenido estático (sin repositorio). TODO: conectar a BD/API si se vuelve dinámico.
    private val _uiState = MutableStateFlow<HomeUiState>(
        HomeUiState.Success(
            fraseMaestra = "Ningún dios debería poder borrar a quienes se atreven a decirle que no",
            logline = "Los dioses pierden fragmentos de su identidad, los Aspectos Divinos. Un ser marcado por grietas divinas deberá encontrarlos y restaurarlos en un mundo alterado por la fractura.",
            avisoLore = "Lore v1.0, contenido propuesto",
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
