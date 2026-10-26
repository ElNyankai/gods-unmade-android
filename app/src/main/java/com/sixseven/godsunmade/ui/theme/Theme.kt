package com.sixseven.godsunmade.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val GodsColorScheme = darkColorScheme(
    primary = Gold,
    onPrimary = Background,
    secondary = Crimson,
    onSecondary = TextPrimary,
    tertiary = GoldLight,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = SecondaryBlue,
    onSurfaceVariant = TextSecondary,
    outline = Gold
)

@Composable
fun GodsUnmadeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GodsColorScheme,
        typography = GodsTypography,
        content = content
    )
}