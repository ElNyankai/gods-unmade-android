package com.sixseven.godsunmade.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.SurfaceAlpha

@Composable
fun GodsCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val lift = with(LocalDensity.current) { 4.dp.toPx() }
    val ty by animateFloatAsState(if (pressed) -lift else 0f, label = "lift")
    val shape = RoundedCornerShape(0.dp)

    Box(
        modifier = modifier
            .graphicsLayer { translationY = ty }
            .hardShadow(4.dp)
            .background(SurfaceAlpha, shape)
            .border(2.dp, Gold, shape)
            .then(
                if (onClick != null)
                    Modifier.clickable(
                        interactionSource = source,
                        indication = null,
                        onClick = onClick
                    )
                else Modifier
            )
    ) {
        content()
    }
}