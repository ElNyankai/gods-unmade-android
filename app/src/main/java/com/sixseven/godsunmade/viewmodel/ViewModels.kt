package com.sixseven.godsunmade.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sixseven.godsunmade.data.AppModule
import com.sixseven.godsunmade.data.Chapter
import com.sixseven.godsunmade.data.Character
import com.sixseven.godsunmade.data.ControlItem
import com.sixseven.godsunmade.data.GameData
import com.sixseven.godsunmade.data.GameInfo
import com.sixseven.godsunmade.data.GodsRepository
import com.sixseven.godsunmade.data.WikiEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo: GodsRepository = AppModule.repository
) : ViewModel() {
    private val _game = MutableStateFlow<GameInfo?>(null)
    val game: StateFlow<GameInfo?> = _game.asStateFlow()

    init {
        viewModelScope.launch { _game.value = repo.getGameInfo() }
    }
}

class GamesViewModel(
    private val repo: GodsRepository = AppModule.repository
) : ViewModel() {
    private val _game = MutableStateFlow<GameInfo?>(null)
    val game: StateFlow<GameInfo?> = _game.asStateFlow()

    private val _controls = MutableStateFlow<List<ControlItem>>(emptyList())
    val controls: StateFlow<List<ControlItem>> = _controls.asStateFlow()

    init {
        viewModelScope.launch {
            _game.value = repo.getGameInfo()
            _controls.value = repo.getControls()
        }
    }
}

class StoryViewModel(
    private val repo: GodsRepository = AppModule.repository
) : ViewModel() {
    private val _chapters = MutableStateFlow<List<Chapter>>(emptyList())
    val chapters: StateFlow<List<Chapter>> = _chapters.asStateFlow()

    init {
        viewModelScope.launch { _chapters.value = repo.getChapters() }
    }
}

class CharactersViewModel(
    private val repo: GodsRepository = AppModule.repository
) : ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    init {
        viewModelScope.launch { _characters.value = repo.getCharacters() }
    }
}

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val repo: GodsRepository = AppModule.repository

    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character.asStateFlow()

    init {
        val id: String = savedStateHandle["id"] ?: ""
        viewModelScope.launch { _character.value = repo.getCharacter(id) }
    }
}

class WikiViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val repo: GodsRepository = AppModule.repository

    val categories: List<String> = GameData.categoriasWiki

    private val entries = MutableStateFlow<List<WikiEntry>>(emptyList())
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _category = MutableStateFlow(savedStateHandle.get<String>("categoria").orEmpty())
    val category: StateFlow<String> = _category.asStateFlow()

    val grouped: StateFlow<Map<String, List<WikiEntry>>> =
        combine(entries, _query, _category) { list, q, cat ->
            list
                .filter { e ->
                    (cat.isBlank() || e.categoria == cat) &&
                            (q.isBlank() ||
                                    e.titulo.contains(q, ignoreCase = true) ||
                                    e.contenido.contains(q, ignoreCase = true) ||
                                    e.categoria.contains(q, ignoreCase = true))
                }
                .sortedBy { categories.indexOf(it.categoria) }
                .groupBy { it.categoria }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    init {
        viewModelScope.launch { entries.value = repo.getWikiEntries() }
    }

    fun onQueryChange(q: String) {
        _query.value = q
    }

    /** Tocar la categoría activa la desactiva (vuelve a TODAS). */
    fun onCategoryChange(c: String) {
        _category.value = if (_category.value == c) "" else c
    }
}