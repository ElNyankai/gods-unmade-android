package com.sixseven.godsunmade.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Ajusta rutas y nombres de campos a los serializers de tu Django REST Framework.
interface GodsApi {
    @GET("game/")
    suspend fun game(): GameInfo

    @GET("controls/")
    suspend fun controls(): List<ControlItem>

    @GET("chapters/")
    suspend fun chapters(): List<Chapter>

    @GET("characters/")
    suspend fun characters(): List<Character>

    @GET("characters/{id}/")
    suspend fun character(@Path("id") id: String): Character

    @GET("regions/")
    suspend fun regions(): List<Region>

    @GET("wiki/")
    suspend fun wiki(): List<WikiEntry>

    @GET("team/")
    suspend fun team(): List<String>
}

object ApiProvider {
    // 10.0.2.2 = localhost de tu PC desde el emulador Android
    val api: GodsApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GodsApi::class.java)
    }
}