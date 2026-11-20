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

/** Lee un JSON de `assets` y lo cachea en memoria. */
class AssetJsonSource<T>(
    private val context: Context,
    private val fileName: String,
    private val decode: (String) -> T,
) {
    private val mutex = Mutex()
    private var cache: T? = null

    // El mutex evita leer el asset dos veces en llamadas concurrentes; después usa la cache.
    suspend fun get(): T = mutex.withLock {
        cache ?: withContext(Dispatchers.IO) {
            context.assets.open(fileName).bufferedReader(Charsets.UTF_8).use { decode(it.readText()) }
        }.also { cache = it }
    }
}

// ignoreUnknownKeys permite que el JSON tenga campos extra sin romper la carga.
private val json = Json { ignoreUnknownKeys = true }

private inline fun <reified T> decodeList(text: String): List<T> =
    json.decodeFromString(json.serializersModule.serializer<List<T>>(), text)

/** Personajes leídos de assets/characters.json. */
class LocalCharactersRepository(context: Context) : CharactersRepository {
    private val source = AssetJsonSource(context, "characters.json") { decodeList<Character>(it) }
    override suspend fun getCharacters() = source.get()
    override suspend fun getCharacter(id: String) = source.get().firstOrNull { it.id == id }
}

/** Capítulos leídos de assets/story.json; se devuelven ordenados por `orden`. */
class LocalStoryRepository(context: Context) : StoryRepository {
    private val source = AssetJsonSource(context, "story.json") { decodeList<StoryChapter>(it) }
    override suspend fun getChapters() = source.get().sortedBy { it.orden }
    override suspend fun getChapter(id: String) = source.get().firstOrNull { it.id == id }
}

/** Ranking leído de assets/leaderboard.json; se devuelve ordenado por posición. */
class LocalLeaderboardRepository(context: Context) : LeaderboardRepository {
    private val source = AssetJsonSource(context, "leaderboard.json") { decodeList<Player>(it) }
    override suspend fun getPlayers() = source.get().sortedBy { it.posicion }
}
