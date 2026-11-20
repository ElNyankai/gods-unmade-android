package com.sixseven.godsunmade.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.R
import com.sixseven.godsunmade.ui.theme.*

/** Traduce el nombre de imagen del JSON a un drawable. Devuelve null si no existe. */
@DrawableRes
fun drawableFor(name: String?): Int? = when (name) {
    "protagonista" -> R.drawable.protagonista
    "minotauro" -> R.drawable.minotauro
    "arpia" -> R.drawable.arpia
    "medusa" -> R.drawable.medusa
    "custodio" -> R.drawable.custodio
    "aspecto_hermes" -> R.drawable.aspecto_hermes
    "camino_protagonista" -> R.drawable.camino_protagonista
    "fractura_panteon" -> R.drawable.fractura_panteon
    "historia" -> R.drawable.historia
    "fondo_lore" -> R.drawable.fondo_lore
    "captura_habilidades" -> R.drawable.captura_habilidades
    else -> null
}

/** Separador ◆ ◇ ◆ con líneas de bronce, como en la web. */
@Composable
fun OrnamentDivider(modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.width(32.dp).height(1.dp).background(Bronce.copy(alpha = 0.6f)))
        Text("  ◆ ◇ ◆  ", style = MaterialTheme.typography.labelSmall, color = Bronce)
        Box(Modifier.width(32.dp).height(1.dp).background(Bronce.copy(alpha = 0.6f)))
    }
}

/** Cabecera de sección: fondo-lore al 20% de opacidad, kicker, título, separador y texto. */
@Composable
fun LoreHeader(kicker: String, title: String, body: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth()) {
        Image(
            painterResource(R.drawable.fondo_lore), null,
            Modifier.matchParentSize().alpha(0.2f), contentScale = ContentScale.Crop,
        )
        Column(
            Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(kicker.uppercase(), style = MaterialTheme.typography.labelMedium, color = Bronce)
            Text(title.uppercase(), style = MaterialTheme.typography.displayMedium, color = Lino, textAlign = TextAlign.Center)
            OrnamentDivider()
            Text(body, style = MaterialTheme.typography.bodyMedium, color = LinoSuave, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun LoreHeaderPreview() = GodsUnmadeTheme {
    LoreHeader("Registro de personajes", "Personajes", "Un ser marcado por grietas divinas…")
}
