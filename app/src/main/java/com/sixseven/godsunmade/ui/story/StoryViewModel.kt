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

sealed interface StoryUiState {
    data object Loading : StoryUiState
    data class Success(
        val destacados: List<StoryChapter>,
        val secciones: List<Pair<StorySection, List<StoryChapter>>>,
    ) : StoryUiState
    data class Error(val mensaje: String) : StoryUiState
}

sealed interface ChapterUiState {
    data object Loading : ChapterUiState
    data class Success(val capitulo: StoryChapter, val spoilerAceptado: Boolean) : ChapterUiState
    data class Error(val mensaje: String) : ChapterUiState
}

class StoryViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val uiState: StateFlow<StoryUiState> = _uiState

    init { cargar() }

    fun cargar() {
        _uiState.value = StoryUiState.Loading
        viewModelScope.launch {
            _uiState.value = runCatching {
                val capitulos = repository.getChapters()
                val porSeccion = capitulos.groupBy { it.section }
                StoryUiState.Success(
                    destacados = capitulos.filter { it.destacado },
                    secciones = StorySection.entries.mapNotNull { s -> porSeccion[s]?.let { s to it } },
                )
            }.getOrElse { StoryUiState.Error(it.message ?: "Error al cargar") }
        }
    }
}

class ChapterDetailViewModel(
    private val repository: StoryRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
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

    fun aceptarSpoiler() = _uiState.update { s ->
        if (s is ChapterUiState.Success) s.copy(spoilerAceptado = true) else s
    }
}
