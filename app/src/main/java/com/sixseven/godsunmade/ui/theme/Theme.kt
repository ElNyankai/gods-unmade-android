package com.sixseven.godsunmade.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// colores que usa Material para toda la app
private val EsquemaOscuro = darkColorScheme(
    primary = Arcilla,
    onPrimary = Lino,
    primaryContainer = ArcillaOscura,
    onPrimaryContainer = Lino,
    secondary = Bronce,
    onSecondary = Sombra,
    secondaryContainer = SuperficieAlta,
    onSecondaryContainer = BronceClaro,
    tertiary = BronceClaro,
    background = Fondo,
    onBackground = Lino,
    surface = Superficie,
    onSurface = Lino,
    surfaceVariant = SuperficieAlta,
    onSurfaceVariant = LinoSuave,
    outline = Bronce,
    outlineVariant = Piedra,
    error = Arcilla,
)

// como la web es oscura, aqui siempre usamos el tema oscuro
@Composable
fun GodsUnmadeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EsquemaOscuro, typography = Typography, content = content)
}
