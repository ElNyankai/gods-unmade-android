package com.sixseven.godsunmade.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sixseven.godsunmade.AppContainer
import com.sixseven.godsunmade.GodsUnmadeApp
import com.sixseven.godsunmade.ui.characters.CharacterDetailViewModel
import com.sixseven.godsunmade.ui.characters.CharactersViewModel
import com.sixseven.godsunmade.ui.home.HomeViewModel
import com.sixseven.godsunmade.ui.leaderboard.LeaderboardViewModel
import com.sixseven.godsunmade.ui.story.ChapterDetailViewModel
import com.sixseven.godsunmade.ui.story.StoryViewModel

private fun CreationExtras.container(): AppContainer =
    (this[APPLICATION_KEY] as GodsUnmadeApp).container

// Los argumentos de ruta (id) llegan por SavedStateHandle a los ViewModels de detalle.
private fun CreationExtras.args(): SavedStateHandle = createSavedStateHandle()

/** Factory que crea los ViewModels de cada ruta con las dependencias del AppContainer. */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { HomeViewModel() }
        initializer { CharactersViewModel(container().charactersRepository) }
        initializer { CharacterDetailViewModel(container().charactersRepository, args()) }
        initializer { StoryViewModel(container().storyRepository) }
        initializer { ChapterDetailViewModel(container().storyRepository, args()) }
        initializer { LeaderboardViewModel(container().leaderboardRepository) }
    }
}
