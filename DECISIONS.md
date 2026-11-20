# Decisiones
- JAVA_HOME no estaba definido: se compila con el JDK de Android Studio (jbr).
- La web no tiene modelos ni logo en archivo: se dibujó el glifo ✦ (como en la web) como ícono del launcher.
- Las imágenes de criaturas de la web (arpía, medusa, minotauro, custodio) no coinciden con los Restos de Función del lore: se copiaron pero no se asignan a personajes. Solo el protagonista usa imagen.
- Capítulos "Elethra", "La Tacha" y "Acto II" marcados con spoilers (revelan el crimen central).
- Tipografía: monoespaciada para títulos (como la web), sans del sistema para texto.
- Rutas de navegación como strings; los ViewModels de detalle reciben el id vía SavedStateHandle.
- El tema es siempre oscuro (la web es oscura).

## Rediseño Personajes e Historia
- Personajes: solo los 5 de la web (protagonista, Minotauro, Arpía, Medusa, Custodio), textos copiados de `personajes.html`. Se eliminaron categorías, relaciones, aspecto divino, chips y búsqueda (5 elementos: la búsqueda estorbaba). El modelo `Character` ahora tiene registro/tipo/etiqueta/objetivo.
- Las imágenes `criatura_*` anteriores se reemplazaron por `minotauro`, `arpia`, `medusa`, `custodio`.
- Historia: `story.json` gana `imagen`, `arte` y `destacado`. Imagen de la web donde encaja (historia, fondo_lore, camino_protagonista, aspecto_hermes, fractura_panteon, captura_mundo, captura_combate para los Restos de Función); si no, arte generado con `ChapterArt` (camino, mar, flecha, forja, umbral, hogar, tacha, balanza, aguja, concordancia, escudo). Los 20 capítulos tienen imagen o arte.
- `captura_habilidades` se copió pero no se usa: ningún capítulo trata de habilidades.
- "Acto II" pasa a `tieneSpoilers=false`: no revela el crimen central (solo insinúa sellos de Hefesto).
- Cabecera de capítulo "collapsing" con parallax y desvanecido al hacer scroll (la barra superior se mantiene).
- Inicio: solo se corrigió el subtítulo del acceso a Personajes, que mencionaba dioses y mortales.
- Este rediseño se hizo sin subagentes Haiku: los cambios de datos eran pocos y el contexto ya estaba cargado.

## Ajuste puntual Historia
- "Regiones de Lárion" (arte `mapa`: península, montañas y brújula) y "Restos de Función" (arte `fragmentos`: anillo roto, esquirlas y eco) pasan de imagen web a `ChapterArt`; ya son 13 símbolos.
- Se eliminaron `captura_mundo` y `captura_combate` de `drawable` y de `drawableFor()` porque ningún capítulo las usa. `captura_habilidades` sigue (no se tocó).
