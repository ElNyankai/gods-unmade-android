package com.sixseven.godsunmade.data.repository

import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.data.model.StoryChapter

// Contratos de datos. La UI y los ViewModels solo conocen estas interfaces.
// TODO: conectar a BD/API (crear Remote*Repository con Retrofit y cambiarlo en AppContainer).

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: String): Character?
}

interface StoryRepository {
    suspend fun getChapters(): List<StoryChapter>
    suspend fun getChapter(id: String): StoryChapter?
}

interface LeaderboardRepository {
    suspend fun getPlayers(): List<Player>
}
