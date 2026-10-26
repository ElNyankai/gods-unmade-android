package com.sixseven.godsunmade.data

object GameData {

    const val POR_REVELAR = "Por revelar"

    val equipo = listOf(
        "Lesly Citlally Alvarado García",
        "Paulina Hernández Trejo",
        "Roberto Gael Aguilar López",
        "Ernesto Antonio Avellaneda Ávila",
        "Ángel Javier Correa Ziranda"
    )

    val juego = GameInfo(
        titulo = "Gods Unmade",
        genero = "Metroidvania 2D",
        plataforma = "PC (Unity)",
        estado = "Demo en desarrollo",
        descripcion = "Proyecto escolar de diseño de videojuego. Metroidvania 2D de acción y " +
                "aventura inspirado en la mitología griega.",
        objetivo = "Explorar ruinas inspiradas en Grecia, recuperar un Aspecto Divino perdido " +
                "y desbloquear una nueva habilidad para seguir avanzando.",
        comoSeJuega = "El jugador explora un mapa 2D conectado por habitaciones, derrota enemigos, " +
                "evita obstáculos y busca el Aspecto Divino que le permitirá progresar. La demo " +
                "combina exploración, combate sencillo y desbloqueo de habilidad.",
        reglas = listOf(
            "Se pierde vida al recibir daño de enemigos o al caer en zonas peligrosas.",
            "Hay caminos bloqueados y huecos que no se pueden cruzar al inicio.",
            "El dash se desbloquea al obtener el Aspecto de Hermes.",
            "La demo se completa al obtener la habilidad, avanzar con ella y llegar al cierre " +
                    "narrativo con el encuentro importante de la región."
        )
    )

    val controles = listOf(
        ControlItem("Movimiento", "Teclas W, A, S, D"),
        ControlItem("Salto", "Barra espaciadora"),
        ControlItem("Ataques direccionales", "Botón de ataque + W, A, S o D"),
        ControlItem("Dash", "Se desbloquea al obtener el Aspecto de Hermes")
    )

    val personajes = listOf(
        Character(
            id = "protagonista",
            nombre = "Protagonista",
            rol = "Protagonista",
            categoria = "PROTAGONISTA",
            descripcion = "Ser semihumano con grietas divinas en el cuerpo.",
            rolEnJuego = "Su misión es restaurar los Aspectos Divinos perdidos. Posee la " +
                    "capacidad única de encontrarlos y restaurarlos.",
            drawable = "personaje_protagonista"
        ),
        Character(
            id = "minotauro",
            nombre = "Minotauro",
            rol = "Enemigo terrestre pesado",
            categoria = "ENEMIGO",
            descripcion = "Enemigo terrestre pesado que obliga al jugador a aprender el ritmo " +
                    "de combate y la esquiva.",
            rolEnJuego = "Enseña el ritmo de combate y la esquiva.",
            drawable = "personaje_minotauro"
        ),
        Character(
            id = "arpia",
            nombre = "Arpía",
            rol = "Enemigo volador",
            categoria = "ENEMIGO",
            descripcion = "Enemigo volador que presiona al jugador desde el aire.",
            rolEnJuego = "Ayuda a practicar ataques en distintas direcciones.",
            drawable = "personaje_arpia"
        ),
        Character(
            id = "medusa",
            nombre = "Medusa",
            rol = "Enemigo serpentino",
            categoria = "ENEMIGO",
            descripcion = "Enemigo serpentino que aporta variedad visual.",
            rolEnJuego = "Es una amenaza intermedia dentro de las ruinas.",
            drawable = "personaje_medusa"
        ),
        Character(
            id = "custodio",
            nombre = "Custodio",
            rol = "Jefe de la demo",
            categoria = "JEFE",
            descripcion = "Guardián principal de la demo.",
            rolEnJuego = "Es el reto más fuerte antes del cierre de la primera región.",
            drawable = "personaje_custodio"
        )
    )

    val regiones = listOf(
        Region(
            id = "camino_roto",
            nombre = "Camino Roto",
            descripcion = "Etapa 1. Zona inicial de ruinas, patios y corredores. Se aprende " +
                    "movimiento, combate básico y exploración del mapa."
        ),
        Region(
            id = "santuario_hermes",
            nombre = "Santuario de Hermes",
            descripcion = "Etapa 2. Área clave donde se descubre el Aspecto Divino de Hermes. " +
                    "Al restaurarlo, el jugador obtiene el dash y accede a nuevas rutas."
        )
    )

    val capitulos = listOf(
        Chapter(
            id = "aspectos",
            titulo = "Los Aspectos Divinos",
            texto = "Los dioses del panteón griego comienzan a perder fragmentos de su identidad " +
                    "y de sus poderes. Esos fragmentos se conocen como Aspectos Divinos.\n\n" +
                    "Sin ellos, lo sagrado deja de ser estable: los templos, los caminos y los " +
                    "guardianes sagrados quedan alterados.\n\n" +
                    "Esta fractura es el origen del viaje que se cuenta en la demo."
        ),
        Chapter(
            id = "protagonista",
            titulo = "El protagonista marcado",
            texto = "El protagonista es un ser semihumano marcado por grietas divinas en el cuerpo.\n\n" +
                    "Posee una capacidad única: encontrar y restaurar los Aspectos Divinos que los " +
                    "dioses han perdido. Esa es su misión.\n\n" +
                    "Su nombre, su pasado y el origen de sus grietas: $POR_REVELAR."
        ),
        Chapter(
            id = "camino_roto",
            titulo = "El Camino Roto",
            texto = "El viaje de la demo inicia en la región del Camino Roto, una zona de ruinas, " +
                    "patios y corredores.\n\n" +
                    "Aquí el jugador aprende el movimiento, el combate básico y la exploración del " +
                    "mapa mientras atraviesa ruinas y santuarios.\n\n" +
                    "A lo largo de la demo se enfrentará a enemigos como el Minotauro, la Arpía y " +
                    "Medusa, y deberá evitar obstáculos. Algunos caminos y huecos no se pueden " +
                    "cruzar al inicio."
        ),
        Chapter(
            id = "hermes",
            titulo = "El Aspecto de Hermes",
            texto = "Tras recorrer las ruinas y los santuarios, el protagonista llega al Santuario " +
                    "de Hermes, un área clave de la demo.\n\n" +
                    "Allí descubre el Aspecto Divino de Hermes. Al restaurarlo, obtiene el dash.\n\n" +
                    "Con esta habilidad accede a nuevas rutas y puede seguir avanzando."
        ),
        Chapter(
            id = "amenaza",
            titulo = "La amenaza mayor",
            texto = "La demo avanza hasta su cierre narrativo, con el encuentro importante de la " +
                    "región. Antes de ese cierre, el Custodio, guardián principal de la demo, " +
                    "representa el reto más fuerte.\n\n" +
                    "Ese desenlace revela que existe una amenaza mayor detrás de la fractura del panteón.\n\n" +
                    "Quién o qué es esa amenaza: $POR_REVELAR."
        )
    )

    val categoriasWiki = listOf(
        "ASPECTOS DIVINOS", "PERSONAJES", "ENEMIGOS", "REGIONES",
        "MECÁNICAS", "CONTROLES", "EQUIPO"
    )

    // Se arma al final para usar las listas anteriores (el orden importa en un object).
    val wiki: List<WikiEntry> = buildList {
        add(
            WikiEntry(
                "asp_general", "Aspectos Divinos", "ASPECTOS DIVINOS",
                "Fragmentos de la identidad y de los poderes de los dioses del panteón griego. " +
                        "Al perderlos, templos, caminos y guardianes sagrados quedan alterados."
            )
        )
        add(
            WikiEntry(
                "asp_hermes", "Aspecto de Hermes", "ASPECTOS DIVINOS",
                "Aspecto Divino que se descubre en el Santuario de Hermes. Al restaurarlo, el " +
                        "jugador obtiene el dash y accede a nuevas rutas."
            )
        )

        personajes.filter { it.categoria == "PROTAGONISTA" }.forEach {
            add(WikiEntry("pj_${it.id}", it.nombre, "PERSONAJES", "${it.descripcion} ${it.rolEnJuego}"))
        }
        personajes.filter { it.categoria != "PROTAGONISTA" }.forEach {
            add(WikiEntry("en_${it.id}", it.nombre, "ENEMIGOS", "${it.rol}. ${it.descripcion} ${it.rolEnJuego}"))
        }

        regiones.forEach {
            add(WikiEntry("rg_${it.id}", it.nombre, "REGIONES", it.descripcion))
        }

        add(WikiEntry("me_objetivo", "Objetivo", "MECÁNICAS", juego.objetivo))
        add(WikiEntry("me_exploracion", "Exploración", "MECÁNICAS", juego.comoSeJuega))
        add(
            WikiEntry(
                "me_dash", "Dash", "MECÁNICAS",
                "Habilidad que se desbloquea al obtener el Aspecto de Hermes. Permite acceder a " +
                        "nuevas rutas y seguir avanzando."
            )
        )
        add(
            WikiEntry(
                "me_combate", "Combate", "MECÁNICAS",
                "Combate sencillo: el jugador derrota enemigos con ataques direccionales " +
                        "(botón de ataque + W, A, S o D)."
            )
        )
        add(WikiEntry("me_vida", "Vida y peligros", "MECÁNICAS", juego.reglas[0]))
        add(WikiEntry("me_bloqueos", "Caminos bloqueados", "MECÁNICAS", juego.reglas[1]))
        add(WikiEntry("me_cierre", "Cierre de la demo", "MECÁNICAS", juego.reglas[3]))

        controles.forEachIndexed { i, c ->
            add(WikiEntry("ct_$i", c.funcion, "CONTROLES", c.accion))
        }

        add(
            WikiEntry(
                "eq_proyecto", "Proyecto escolar", "EQUIPO",
                "Gods Unmade es un proyecto escolar de diseño de videojuego. Plataforma: PC " +
                        "(Unity). Esta app móvil es complementaria, no es el juego."
            )
        )
        equipo.forEachIndexed { i, nombre ->
            add(
                WikiEntry(
                    "eq_$i", nombre, "EQUIPO",
                    "Integrante del equipo de desarrollo. Rol: $POR_REVELAR."
                )
            )
        }
    }
}