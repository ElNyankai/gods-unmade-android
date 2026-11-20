package com.sixseven.godsunmade.ui.story

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixseven.godsunmade.data.model.StoryChapter
import com.sixseven.godsunmade.data.model.StorySection
import com.sixseven.godsunmade.data.repository.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** Estados de la lista de historia: Loading, Success con destacados y secciones, o Error. */
sealed interface StoryUiState {
    data object Loading : StoryUiState
    data class Success(
        val destacados: List<StoryChapter>,
        val secciones: List<Pair<StorySection, List<StoryChapter>>>,
    ) : StoryUiState
    data class Error(val mensaje: String) : StoryUiState
}

/** Estados de un capítulo: Loading, Success con el capítulo y si el spoiler fue aceptado, o Error. */
sealed interface ChapterUiState {
    data object Loading : ChapterUiState
    data class Success(val capitulo: StoryChapter, val spoilerAceptado: Boolean) : ChapterUiState
    data class Error(val mensaje: String) : ChapterUiState
}

/** ViewModel de la historia: carga los capítulos y los agrupa por sección. */
class StoryViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val uiState: StateFlow<StoryUiState> = _uiState

    init { cargar() }

    fun cargar() {
        _uiState.value = StoryUiState.Loading
        viewModelScope.launch {
            _uiState.value = runCatching {
                val capitulos = repository.getChapters()
                // Agrupa por sección en el orden de StorySection; los destacados se filtran aparte.
                val porSeccion = capitulos.groupBy { it.section }
                StoryUiState.Success(
                    destacados = capitulos.filter { it.destacado },
                    secciones = StorySection.entries.mapNotNull { s -> porSeccion[s]?.let { s to it } },
                )
            }.getOrElse { StoryUiState.Error(it.message ?: "Error al cargar") }
        }
    }
}

/** ViewModel del detalle de capítulo: carga el capítulo indicado y gestiona la aceptación de spoilers. */
class ChapterDetailViewModel(
    private val repository: StoryRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    // Id de la ruta capitulo/{id}, leído del SavedStateHandle.
    private val id: String = checkNotNull(savedStateHandle["id"])
    private val _uiState = MutableStateFlow<ChapterUiState>(ChapterUiState.Loading)
    val uiState: StateFlow<ChapterUiState> = _uiState

    init { cargar() }

    fun cargar() {
        _uiState.value = ChapterUiState.Loading
        viewModelScope.launch {
            _uiState.value = runCatching {
                val c = repository.getChapter(id) ?: error("Capítulo no encontrado")
                ChapterUiState.Success(c, spoilerAceptado = !c.tieneSpoilers)
            }.getOrElse { ChapterUiState.Error(it.message ?: "Error al cargar") }
        }
    }

    // Solo marca el spoiler como aceptado; no vuelve a leer el capítulo.
    fun aceptarSpoiler() = _uiState.update { s ->
        if (s is ChapterUiState.Success) s.copy(spoilerAceptado = true) else s
    }
}
