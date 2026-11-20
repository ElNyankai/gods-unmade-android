package com.sixseven.godsunmade.data.local

import android.content.Context
import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.data.model.StoryChapter
import com.sixseven.godsunmade.data.repository.CharactersRepository
import com.sixseven.godsunmade.data.repository.LeaderboardRepository
import com.sixseven.godsunmade.data.repository.StoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

// lee un json de assets y guarda el resultado en memoria
class AssetJsonSource<T>(
    private val context: Context,
    private val fileName: String,
    private val decode: (String) -> T,
) {
    private val mutex = Mutex()
    private var cache: T? = null

    // si ya lo leimos usamos la copia, si no lo abrimos de assets
    suspend fun get(): T = mutex.withLock {
        cache ?: withContext(Dispatchers.IO) {
            context.assets.open(fileName).bufferedReader(Charsets.UTF_8).use { decode(it.readText()) }
        }.also { cache = it }
    }
}

// el json puede traer campos de mas y no pasa nada
private val json = Json { ignoreUnknownKeys = true }

// convierte el texto del json en una lista del tipo pedido
private inline fun <reified T> decodeList(text: String): List<T> =
    json.decodeFromString(json.serializersModule.serializer<List<T>>(), text)

// aqui leemos el archivo characters.json de assets
class LocalCharactersRepository(context: Context) : CharactersRepository {
    private val source = AssetJsonSource(context, "characters.json") { decodeList<Character>(it) }
    override suspend fun getCharacters() = source.get()
    override suspend fun getCharacter(id: String) = source.get().firstOrNull { it.id == id }
}

// los capitulos vienen de story.json y se ordenan por el campo orden
class LocalStoryRepository(context: Context) : StoryRepository {
    private val source = AssetJsonSource(context, "story.json") { decodeList<StoryChapter>(it) }
    override suspend fun getChapters() = source.get().sortedBy { it.orden }
    override suspend fun getChapter(id: String) = source.get().firstOrNull { it.id == id }
}

// el ranking sale de leaderboard.json, ordenado por posicion
class LocalLeaderboardRepository(context: Context) : LeaderboardRepository {
    private val source = AssetJsonSource(context, "leaderboard.json") { decodeList<Player>(it) }
    override suspend fun getPlayers() = source.get().sortedBy { it.posicion }
}
