package com.sixseven.godsunmade.data

data class GameInfo(
    val titulo: String,
    val genero: String,
    val plataforma: String,
    val estado: String,
    val descripcion: String,
    val objetivo: String,
    val comoSeJuega: String,
    val reglas: List<String>
)

data class Character(
    val id: String,
    val nombre: String,
    val rol: String,
    val categoria: String, // PROTAGONISTA, ENEMIGO o JEFE
    val descripcion: String,
    val rolEnJuego: String,
    val drawable: String   // nombre del drawable, ej. personaje_minotauro
)

data class Chapter(
    val id: String,
    val titulo: String,
    val texto: String // párrafos separados por una línea en blanco
)

data class Region(
    val id: String,
    val nombre: String,
    val descripcion: String
)

data class WikiEntry(
    val id: String,
    val titulo: String,
    val categoria: String,
    val contenido: String
)

data class ControlItem(
    val funcion: String,
    val accion: String
)