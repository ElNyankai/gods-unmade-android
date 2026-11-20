package com.sixseven.godsunmade.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val Titulos = FontFamily.Monospace
private val Cuerpo = FontFamily.SansSerif

// titulos en monoespaciada y texto normal en sans, como en la web
val Typography = Typography(
    displayMedium = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Black, fontSize = 40.sp, letterSpacing = 2.sp),
    headlineMedium = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Black, fontSize = 26.sp, letterSpacing = 1.sp),
    headlineSmall = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleLarge = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 20.sp),
    titleMedium = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 16.sp),
    titleSmall = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 14.sp),
    bodyLarge = TextStyle(fontFamily = Cuerpo, fontSize = 17.sp, lineHeight = 28.sp),
    bodyMedium = TextStyle(fontFamily = Cuerpo, fontSize = 14.sp, lineHeight = 22.sp),
    bodySmall = TextStyle(fontFamily = Cuerpo, fontSize = 12.sp, lineHeight = 18.sp),
    labelLarge = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 13.sp, letterSpacing = 1.sp),
    labelMedium = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 1.5.sp),
    labelSmall = TextStyle(fontFamily = Titulos, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 1.5.sp),
)
