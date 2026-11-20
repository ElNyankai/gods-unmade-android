package com.sixseven.godsunmade.data.repository

import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.data.model.StoryChapter

// TODO: conectar a BD/API (crear Remote*Repository con Retrofit y cambiarlo en AppContainer).

// lo que la app necesita de los personajes, sin importar de donde vienen
interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: String): Character?
}

// capitulos de la historia, a la UI no le importa de donde salgan
interface StoryRepository {
    suspend fun getChapters(): List<StoryChapter>
    suspend fun getChapter(id: String): StoryChapter?
}

// datos del ranking que muestra la pantalla Top
interface LeaderboardRepository {
    suspend fun getPlayers(): List<Player>
}
