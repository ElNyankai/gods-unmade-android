package com.sixseven.godsunmade.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val GodsMono = FontFamily.Monospace

val GodsTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = GodsMono,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        letterSpacing = 0.1.em,
        color = TextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily = GodsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = 0.05.em,
        color = TextPrimary
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        color = TextSecondary
    ),
    labelMedium = TextStyle(
        fontFamily = GodsMono,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.1.em,
        color = Gold
    )
)