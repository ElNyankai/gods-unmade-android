package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GoldLight
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.SurfaceAlpha
import com.sixseven.godsunmade.ui.theme.TextSecondary

data class BottomItem(val label: String, val route: String)

@Composable
fun GodsBottomBar(
    items: List<BottomItem>,
    selectedRoute: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceAlpha)
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Gold)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val selected = item.route == selectedRoute
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSelect(item.route) }
                        .drawBehind {
                            val h = 2.dp.toPx()
                            drawLine(
                                color = if (selected) Gold else Color.Transparent,
                                start = Offset(8.dp.toPx(), size.height - h / 2),
                                end = Offset(size.width - 8.dp.toPx(), size.height - h / 2),
                                strokeWidth = h
                            )
                        }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.label.uppercase(),
                        color = if (selected) GoldLight else TextSecondary,
                        fontFamily = GodsMono,
                        fontWeight = if (selected) FontWeight.Black else FontWeight.Bold,
                        fontSize = 10.sp,
                        letterSpacing = 0.02.em,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}