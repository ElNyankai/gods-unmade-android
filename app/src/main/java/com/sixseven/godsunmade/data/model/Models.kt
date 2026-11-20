package com.sixseven.godsunmade.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: String,
    val nombre: String,
    val registro: String,
    val tipo: String, // "principal" | "enemigo" | "jefe"
    val etiqueta: String? = null,
    val descripcion: String,
    val objetivo: String? = null,
    val imagen: String? = null,
) {
    val esPrincipal: Boolean get() = tipo == "principal"
    val esJefe: Boolean get() = tipo == "jefe"
}

enum class StorySection(val key: String, val label: String, val descripcion: String) {
    FUNDAMENTOS("fundamentos", "Fundamentos", "Las leyes y la herida original del universo"),
    CAMPANA("campana", "Campaña", "El recorrido del Testigo, del prólogo al juicio"),
    DESENLACE("desenlace", "Desenlace", "Lo que queda cuando la Concordancia se abre"),
    MUNDO("mundo", "El mundo", "Regiones, facciones y vida cotidiana");

    companion object {
        fun fromKey(key: String): StorySection = entries.firstOrNull { it.key == key } ?: MUNDO
    }
}

@Serializable
data class StoryChapter(
    val id: String,
    val orden: Int,
    val titulo: String,
    val resumen: String,
    val contenido: List<String>,
    val tieneSpoilers: Boolean = false,
    val seccion: String,
    val imagen: String? = null,
    val arte: String? = null,
    val destacado: Boolean = false,
) {
    val section: StorySection get() = StorySection.fromKey(seccion)
}

@Serializable
data class Player(
    val posicion: Int,
    val jugador: String,
    val puntaje: Int,
    val avatar: String? = null,
)
