package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.sixseven.godsunmade.ui.theme.Background
import com.sixseven.godsunmade.ui.theme.Surface

@Composable
fun GodsBackground(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val resId = remember {
        context.resources.getIdentifier("fondo_lore", "drawable", context.packageName)
    }

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        if (resId != 0) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().alpha(0.4f)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background.copy(alpha = 0.7f))
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Background, Surface)))
            )
        }
        content()
    }
}