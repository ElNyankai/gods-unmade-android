package com.sixseven.godsunmade.data.repository

import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.data.model.StoryChapter

// Contratos de datos. La UI y los ViewModels solo conocen estas interfaces.
// TODO: conectar a BD/API (crear Remote*Repository con Retrofit y cambiarlo en AppContainer).

/** Acceso a personajes; la implementación actual lee de assets. */
interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: String): Character?
}

/** Acceso a los capítulos de la historia. */
interface StoryRepository {
    suspend fun getChapters(): List<StoryChapter>
    suspend fun getChapter(id: String): StoryChapter?
}

/** Acceso al ranking de jugadores. */
interface LeaderboardRepository {
    suspend fun getPlayers(): List<Player>
}
