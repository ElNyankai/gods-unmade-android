package com.sixseven.godsunmade

import android.app.Application
import android.content.Context
import com.sixseven.godsunmade.data.local.LocalCharactersRepository
import com.sixseven.godsunmade.data.local.LocalLeaderboardRepository
import com.sixseven.godsunmade.data.local.LocalStoryRepository
import com.sixseven.godsunmade.data.repository.CharactersRepository
import com.sixseven.godsunmade.data.repository.LeaderboardRepository
import com.sixseven.godsunmade.data.repository.StoryRepository

/**
 * Inyección de dependencias manual: crea los repositorios y los expone a los ViewModels.
 *
 * Para conectar una API o BD:
 * 1. Crear Remote*Repository (por ejemplo con Retrofit) que implementen CharactersRepository,
 *    StoryRepository y LeaderboardRepository, devolviendo los mismos modelos de data/model.
 * 2. Reemplazar aquí las implementaciones Local* por las Remote*.
 * 3. Agregar el permiso INTERNET y las dependencias necesarias en Gradle.
 * Los ViewModels y la UI no cambian.
 */
class AppContainer(context: Context) {
    // TODO: conectar a BD/API (sustituir Local* por Remote* sin tocar ViewModels ni UI)
    val charactersRepository: CharactersRepository = LocalCharactersRepository(context)
    val storyRepository: StoryRepository = LocalStoryRepository(context)
    val leaderboardRepository: LeaderboardRepository = LocalLeaderboardRepository(context)
}

/** Application de la app: crea el AppContainer al iniciar el proceso. */
class GodsUnmadeApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
