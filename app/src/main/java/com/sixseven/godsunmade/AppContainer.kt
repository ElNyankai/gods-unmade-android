package com.sixseven.godsunmade

import android.app.Application
import android.content.Context
import com.sixseven.godsunmade.data.local.LocalCharactersRepository
import com.sixseven.godsunmade.data.local.LocalLeaderboardRepository
import com.sixseven.godsunmade.data.local.LocalStoryRepository
import com.sixseven.godsunmade.data.repository.CharactersRepository
import com.sixseven.godsunmade.data.repository.LeaderboardRepository
import com.sixseven.godsunmade.data.repository.StoryRepository

/** Inyección de dependencias manual. Aquí se cambian las implementaciones locales por remotas. */
class AppContainer(context: Context) {
    // TODO: conectar a BD/API (sustituir Local* por Remote* sin tocar ViewModels ni UI)
    val charactersRepository: CharactersRepository = LocalCharactersRepository(context)
    val storyRepository: StoryRepository = LocalStoryRepository(context)
    val leaderboardRepository: LeaderboardRepository = LocalLeaderboardRepository(context)
}

class GodsUnmadeApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
