package com.sixseven.godsunmade.data

interface GodsRepository {
    suspend fun getGameInfo(): GameInfo
    suspend fun getControls(): List<ControlItem>
    suspend fun getChapters(): List<Chapter>
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacter(id: String): Character?
    suspend fun getRegions(): List<Region>
    suspend fun getWikiEntries(): List<WikiEntry>
    suspend fun getTeam(): List<String>
}

/** Datos oficiales locales. Sustituir por un RemoteGodsRepository con Retrofit. */
class FakeGodsRepository : GodsRepository {
    override suspend fun getGameInfo(): GameInfo = GameData.juego
    override suspend fun getControls(): List<ControlItem> = GameData.controles
    override suspend fun getChapters(): List<Chapter> = GameData.capitulos
    override suspend fun getCharacters(): List<Character> = GameData.personajes
    override suspend fun getCharacter(id: String): Character? =
        GameData.personajes.find { it.id == id }
    override suspend fun getRegions(): List<Region> = GameData.regiones
    override suspend fun getWikiEntries(): List<WikiEntry> = GameData.wiki
    override suspend fun getTeam(): List<String> = GameData.equipo
}

object AppModule {
    val repository: GodsRepository by lazy { FakeGodsRepository() }
}