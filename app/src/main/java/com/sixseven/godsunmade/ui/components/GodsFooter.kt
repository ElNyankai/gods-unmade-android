package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.sixseven.godsunmade.data.GameData
import com.sixseven.godsunmade.ui.theme.Crimson
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GoldLight
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.Surface
import com.sixseven.godsunmade.ui.theme.SurfaceAlpha
import com.sixseven.godsunmade.ui.theme.TextMuted
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.ui.theme.TextSecondary

@Composable
fun GodsFooter(
    modifier: Modifier = Modifier,
    team: List<String> = GameData.equipo
) {
    var showTeam by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Gold)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceAlpha)
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "✦ SIXSEVENGAMES ✦",
                color = Gold,
                fontFamily = GodsMono,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                letterSpacing = 0.2.em
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "GODS UNMADE",
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Un mundo olvidado. Un panteón caído. Una historia que aún espera ser descubierta.",
                color = TextSecondary,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(max = 320.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .size(width = 96.dp, height = 1.dp)
                    .background(Crimson)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Proyecto escolar · Equipo de desarrollo",
                color = GoldLight,
                fontFamily = GodsMono,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { showTeam = true }
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "THE UNWRITTEN PANTHEON · 2026",
                color = TextMuted,
                fontFamily = GodsMono,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    if (showTeam) {
        TeamDialog(team = team, onDismiss = { showTeam = false })
    }
}

@Composable
private fun TeamDialog(team: List<String>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.border(2.dp, Gold, RoundedCornerShape(0.dp)),
        shape = RoundedCornerShape(0.dp),
        containerColor = Surface,
        title = {
            Text(
                text = "EQUIPO DE DESARROLLO",
                color = Gold,
                fontFamily = GodsMono,
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                letterSpacing = 0.1.em
            )
        },
        text = {
            Column {
                team.forEach { name ->
                    Text(
                        text = "◆  $name",
                        color = TextPrimary,
                        fontSize = 14.sp,
                        lineHeight = 24.sp
                    )
                }
            }
        },
        confirmButton = {
            GodsButton(text = "CERRAR", onClick = onDismiss)
        }
    )
}