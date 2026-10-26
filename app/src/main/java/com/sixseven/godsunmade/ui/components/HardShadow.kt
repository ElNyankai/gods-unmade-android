package com.sixseven.godsunmade.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.ui.theme.HardShadow

/** Sombra dura sin blur: rectángulo desplazado detrás del componente. */
fun Modifier.hardShadow(
    offset: Dp = 4.dp,
    color: Color = HardShadow,
    enabled: Boolean = true
): Modifier = if (!enabled) this else this.drawBehind {
    val o = offset.toPx()
    drawRect(color = color, topLeft = Offset(o, o), size = size)
}