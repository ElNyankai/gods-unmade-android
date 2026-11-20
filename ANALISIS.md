# Análisis de la web de referencia (GodsUnmadeWeb)

Proyecto Django (PWA) con Tailwind. Sin modelos de datos (`models.py` vacío): todo el contenido está en plantillas HTML.

## Pantallas web
inicio, historia, personajes, top (ranking), juegos, wiki (vacía), login, descarga offline.
**Alcance app:** solo Inicio, Personajes, Historia, Top Global.

## Paleta (tema oscuro)
| Uso | Hex |
|---|---|
| Fondo | `#171C2A` |
| Superficie / header | `#202B40` |
| Bronce (acento, bordes) | `#C49A4A` |
| Rojo arcilla (acento primario) | `#8F2528` |
| Rojo oscuro | `#542027` |
| Texto principal (lino) | `#F4E9D2` |
| Bronce claro (hover) | `#E2C99A` |
| Texto secundario | `#D5C8AD` |
| Texto apagado | `#A9A6A0` |
| Sombra / negro | `#111827` |

## Fuentes
Monoespaciada (`font-mono`) para títulos y UI, mayúsculas y tracking amplio; sans del sistema para el cuerpo. En la app: `FontFamily.Monospace` para títulos.

## Identidad
Logo = glifo "✦" en cuadrado rojo con borde bronce; "GODS" + "U N M A D E". Separadores "◆ ◇ ◆". Estilo pixel/retro con sombras duras.

## Imágenes reutilizadas (copiadas a `res/drawable`)
protagonista.png, portada.jpg, fondo_lore.png, ranking_fondo.jpg, historia_banner.jpg, criatura_{arpia,custodio,medusa,minotauro}.jpg.
No se copian: fotos del equipo, capturas, logo de Steam. No hay logo en archivo: se dibuja el ✦.
