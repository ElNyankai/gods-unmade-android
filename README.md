# Gods Unmade (app Android)

Versión móvil de la wiki del universo de *Gods Unmade*. Kotlin + Jetpack Compose + Material 3, MVVM.
Pantallas: Inicio, Personajes (los 5 de la web: protagonista, Minotauro, Arpía, Medusa y Custodio, con detalle), Historia (hero, destacados, tarjetas ilustradas y capítulos con advertencia de spoilers) y Top Global.

## Correr la app
1. Abrir el proyecto en Android Studio (JDK = JetBrains Runtime incluido) y ejecutar `app`, o por consola:
   ```powershell
   $env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"; .\gradlew.bat assembleDebug
   ```
2. El APK queda en `app/build/outputs/apk/debug/`.

El contenido sale de `app/src/main/assets/*.json` (generado desde `LORE.md`, lore v1.0 propuesto, no canon definitivo).

## Conectar la BD / API después
- Los ViewModels solo conocen las interfaces de `data/repository` (`CharactersRepository`, `StoryRepository`, `LeaderboardRepository`).
- Crear `Remote*Repository` (p. ej. con Retrofit) que devuelvan los mismos modelos de `data/model`.
- Cambiar la implementación en `AppContainer.kt` (busca `TODO: conectar a BD/API`). No hay que tocar ViewModels ni UI.
- Añadir permiso `INTERNET` en el manifest y dependencias Retrofit en `libs.versions.toml`.

## Imágenes y arte de capítulos
`story.json` asigna a cada capítulo `imagen` (drawable de la web) o `arte` (símbolo dibujado por `ChapterArt`). Para añadir imágenes, copiarlas a `res/drawable` y registrarlas en `drawableFor()` (`ui/components/Decor.kt`).
