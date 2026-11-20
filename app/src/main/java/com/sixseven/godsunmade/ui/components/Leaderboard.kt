package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.ui.theme.*

// pone punto cada tres cifras, 98000 se ve como 98.000
fun Int.formatoPuntaje(): String = "%,d".format(this).replace(',', '.')

private val Oro = Color(0xFFE2C99A)
private val Plata = Color(0xFFC7CBD1)
private val BronceMedalla = Color(0xFFB07A45)

// el color de la medalla segun el puesto
private fun medalla(pos: Int) = when (pos) { 1 -> Oro; 2 -> Plata; else -> BronceMedalla }

// un puesto del podio: avatar, nombre, puntaje y el bloque con el numero
@Composable
fun PodiumItem(player: Player, modifier: Modifier = Modifier) {
    val alto = when (player.posicion) { 1 -> 120.dp; 2 -> 96.dp; else -> 80.dp }
    val color = medalla(player.posicion)
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        if (player.posicion == 1) Text("♛", style = MaterialTheme.typography.headlineSmall, color = Oro)
        Box(
            Modifier.size(52.dp).clip(CircleShape).background(SuperficieAlta).border(2.dp, color, CircleShape),
            contentAlignment = Alignment.Center,
        ) { Text(player.jugador.take(1).uppercase(), style = MaterialTheme.typography.titleLarge, color = color) }
        Spacer(Modifier.height(6.dp))
        Text(player.jugador, style = MaterialTheme.typography.labelLarge, color = Lino, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(player.puntaje.formatoPuntaje(), style = MaterialTheme.typography.bodySmall, color = Bronce)
        Spacer(Modifier.height(6.dp))
        Box(
            Modifier.fillMaxWidth().height(alto).background(ArcillaOscura, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                .border(1.dp, color, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)),
            contentAlignment = Alignment.TopCenter,
        ) { Text("${player.posicion}", style = MaterialTheme.typography.displayMedium, color = color) }
    }
}

// fila de la clasificacion con puesto, nombre y puntaje
@Composable
fun LeaderboardRow(player: Player, modifier: Modifier = Modifier) {
    Card(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Superficie),
        border = BorderStroke(1.dp, Bronce.copy(alpha = 0.35f)),
    ) {
        Row(Modifier.padding(horizontal = 14.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("#${player.posicion}", style = MaterialTheme.typography.titleSmall, color = Bronce, modifier = Modifier.width(48.dp))
            Text(player.jugador, style = MaterialTheme.typography.titleSmall, color = Lino, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(player.puntaje.formatoPuntaje(), style = MaterialTheme.typography.titleSmall, color = BronceClaro)
        }
    }
}

@Preview
@Composable
private fun LeaderboardPreview() = GodsUnmadeTheme {
    Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(Modifier.width(300.dp), verticalAlignment = Alignment.Bottom) {
            PodiumItem(Player(2, "Nova", 80000), Modifier.weight(1f))
            PodiumItem(Player(1, "Kratos7", 98000), Modifier.weight(1f))
            PodiumItem(Player(3, "Lyra", 70000), Modifier.weight(1f))
        }
        LeaderboardRow(Player(4, "Rook", 61200))
    }
}
