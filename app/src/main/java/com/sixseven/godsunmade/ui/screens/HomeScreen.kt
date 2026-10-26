package com.sixseven.godsunmade.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sixseven.godsunmade.ui.components.BodyText
import com.sixseven.godsunmade.ui.components.GodsButton
import com.sixseven.godsunmade.ui.components.GodsCard
import com.sixseven.godsunmade.ui.components.GodsFooter
import com.sixseven.godsunmade.ui.components.LabelText
import com.sixseven.godsunmade.ui.components.OrnamentalDivider
import com.sixseven.godsunmade.ui.components.SubTitle
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.viewmodel.HomeViewModel

private data class QuickAccess(
    val title: String,
    val desc: String,
    val route: String,
    val glyph: String
)

private val quickAccess = listOf(
    QuickAccess("Historia", "Los Aspectos Divinos y el panteón fracturado.", "story", "☰"),
    QuickAccess("Personajes", "El protagonista, enemigos y el jefe de la demo.", "characters", "☠"),
    QuickAccess("Escenarios", "Camino Roto y Santuario de Hermes.", "wiki?categoria=REGIONES", "⌂"),
    QuickAccess("Juego", "Género, controles y reglas.", "games", "♜")
)

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    vm: HomeViewModel = viewModel()
) {
    val game by vm.game.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "✦", color = Gold, fontSize = 40.sp, fontFamily = GodsMono)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "GODS UNMADE",
                    color = TextPrimary,
                    fontFamily = GodsMono,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    letterSpacing = 0.15.em,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "METROIDVANIA 2D · MITOLOGÍA GRIEGA",
                    color = Gold,
                    fontFamily = GodsMono,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.15.em,
                    textAlign = TextAlign.Center
                )
                OrnamentalDivider(modifier = Modifier.padding(vertical = 12.dp))
                BodyText(
                    text = "Un mundo olvidado. Un panteón caído. Una historia que aún espera ser descubierta.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 320.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                GodsButton(
                    text = "EXPLORAR EL PANTEÓN",
                    onClick = { onNavigate("characters") }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        game?.let { g ->
            item {
                GodsCard(
                    onClick = { onNavigate("games") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 28.dp, top = 8.dp, bottom = 16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LabelText(text = g.estado)
                        Spacer(modifier = Modifier.height(4.dp))
                        SubTitle(text = "Demo: Camino Roto", color = TextPrimary)
                        Spacer(modifier = Modifier.height(6.dp))
                        BodyText(text = g.objetivo)
                    }
                }
            }
        }

        item {
            SubTitle(
                text = "Acceso rápido",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }

        items(quickAccess) { q ->
            GodsCard(
                onClick = { onNavigate(q.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 28.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = q.glyph, color = Gold, fontSize = 28.sp, fontFamily = GodsMono)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        SubTitle(text = q.title, color = TextPrimary)
                        BodyText(text = q.desc)
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            GodsFooter()
        }
    }
}