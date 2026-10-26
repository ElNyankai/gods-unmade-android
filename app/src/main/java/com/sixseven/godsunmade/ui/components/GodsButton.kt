package com.sixseven.godsunmade.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sixseven.godsunmade.ui.theme.Crimson
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GoldLight
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.TextPrimary

@Composable
fun GodsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shadow: Dp = 3.dp
) {
    val source = remember { MutableInteractionSource() }
    val pressed by source.collectIsPressedAsState()
    val shift by animateDpAsState(if (pressed) 2.dp else 0.dp, label = "press")
    val shape = RoundedCornerShape(0.dp)

    Box(
        modifier = modifier
            .offset(shift, shift)
            .hardShadow(offset = shadow, enabled = !pressed)
            .border(2.dp, if (pressed) GoldLight else Gold, shape)
            .background(Crimson, shape)
            .clickable(interactionSource = source, indication = null, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(),
            color = TextPrimary,
            fontFamily = GodsMono,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            letterSpacing = 0.1.em
        )
    }
}