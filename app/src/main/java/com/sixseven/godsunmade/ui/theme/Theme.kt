package com.sixseven.godsunmade.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// colores de Material con la paleta nueva
private val EsquemaOscuro = darkColorScheme(
    primary = Peligro,
    onPrimary = Sombra,
    primaryContainer = OxidoOscuro,
    onPrimaryContainer = Marfil,
    secondary = Cobre,
    onSecondary = Sombra,
    secondaryContainer = MarronSombra,
    onSecondaryContainer = Oro,
    tertiary = Oro,
    background = Sombra,
    onBackground = Marfil,
    surface = Ciruela,
    onSurface = Marfil,
    surfaceVariant = MarronSombra,
    onSurfaceVariant = Apagado,
    outline = Cobre,
    outlineVariant = Apagado,
    error = Peligro,
)

// la web es oscura, asi que la app siempre usa el tema oscuro
@Composable
fun GodsUnmadeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EsquemaOscuro, typography = Typography, content = content)
}
