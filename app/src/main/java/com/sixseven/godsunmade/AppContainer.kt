package com.sixseven.godsunmade

import android.app.Application
import android.content.Context
import com.sixseven.godsunmade.data.local.LocalCharactersRepository
import com.sixseven.godsunmade.data.local.LocalLeaderboardRepository
import com.sixseven.godsunmade.data.local.LocalStoryRepository
import com.sixseven.godsunmade.data.repository.CharactersRepository
import com.sixseven.godsunmade.data.repository.LeaderboardRepository
import com.sixseven.godsunmade.data.repository.StoryRepository

// aqui se eligen los repositorios que usa la app
// para usar una base de datos o API se cambian estos Local* por otros
class AppContainer(context: Context) {
    // TODO: conectar a BD/API (sustituir Local* por Remote* sin tocar ViewModels ni UI)
    val charactersRepository: CharactersRepository = LocalCharactersRepository(context)
    val storyRepository: StoryRepository = LocalStoryRepository(context)
    val leaderboardRepository: LeaderboardRepository = LocalLeaderboardRepository(context)
}

// se crea una sola vez al abrir la app y guarda el container
class GodsUnmadeApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
