package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.ui.theme.*

// imagen del personaje, si no hay foto mostramos la inicial
@Composable
fun CharacterAvatar(character: Character, modifier: Modifier = Modifier, height: Dp = 200.dp) {
    val res = drawableFor(character.imagen)
    Box(
        modifier.fillMaxWidth().height(height).background(Sombra),
        contentAlignment = Alignment.Center,
    ) {
        if (res != null) {
            Image(
                painterResource(res), character.nombre, Modifier.fillMaxSize(),
                contentScale = if (character.esPrincipal) ContentScale.Fit else ContentScale.Crop,
            )
        } else {
            Text(character.nombre.take(1), style = MaterialTheme.typography.displayMedium, color = Bronce)
        }
        Box(Modifier.matchParentSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent, Sombra.copy(alpha = 0.7f)))))
    }
}

@Composable
fun RegistroLabel(registro: String, modifier: Modifier = Modifier) {
    Text("REGISTRO $registro", modifier, style = MaterialTheme.typography.labelMedium, color = Bronce)
}

@Composable
fun TagLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text.uppercase(),
        modifier.background(Arcilla).border(1.dp, Bronce).padding(horizontal = 8.dp, vertical = 3.dp),
        style = MaterialTheme.typography.labelSmall,
        color = Lino,
    )
}

// recuadro con el objetivo principal del personaje
@Composable
fun ObjectiveBlock(text: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().background(Fondo.copy(alpha = 0.6f)).height(IntrinsicSize.Min)) {
        Box(Modifier.width(4.dp).fillMaxHeight().background(Arcilla))
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("OBJETIVO PRINCIPAL", style = MaterialTheme.typography.labelMedium, color = Bronce)
            Text(text, style = MaterialTheme.typography.bodyMedium, color = Lino)
        }
    }
}

private val cardShape = RoundedCornerShape(2.dp)

// tarjeta de enemigo o jefe que sale en la lista
@Composable
fun CharacterCard(character: Character, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = cardShape,
        colors = CardDefaults.cardColors(containerColor = Superficie),
        border = BorderStroke(if (character.esJefe) 2.dp else 1.dp, Bronce),
    ) {
        CharacterAvatar(character, height = if (character.esJefe) 240.dp else 190.dp)
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            RegistroLabel(character.registro)
            Text(character.nombre.uppercase(), style = MaterialTheme.typography.titleLarge, color = Lino)
            if (character.etiqueta != null) TagLabel(character.etiqueta)
            Text(character.descripcion, style = MaterialTheme.typography.bodyMedium, color = LinoSuave)
        }
    }
}

// tarjeta grande del protagonista, con su objetivo abajo
@Composable
fun FeaturedCharacterCard(character: Character, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = cardShape,
        colors = CardDefaults.cardColors(containerColor = Superficie),
        border = BorderStroke(2.dp, Bronce),
    ) {
        Box {
            CharacterAvatar(character, height = 300.dp)
            Text(
                "GODS UNMADE · REGISTRO ${character.registro}",
                Modifier.align(Alignment.BottomStart).padding(12.dp),
                style = MaterialTheme.typography.labelSmall,
                color = Bronce,
            )
        }
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if (character.etiqueta != null) TagLabel(character.etiqueta)
            Text(character.nombre, style = MaterialTheme.typography.headlineMedium, color = Lino)
            Text(character.descripcion, style = MaterialTheme.typography.bodyMedium, color = LinoSuave)
            if (character.objetivo != null) ObjectiveBlock(character.objetivo)
        }
    }
}

private val previewHero = Character(
    "protagonista", "Protagonista", "001", "principal", "PERSONAJE PRINCIPAL",
    "Es un ser semihumano marcado por grietas divinas.", "Recuperar el Aspecto Divino de Hermes.", "protagonista",
)
private val previewBoss = Character("custodio", "Custodio", "005", "jefe", "JEFE DE LA DEMO", "Guardián principal de la demo.", null, "custodio")

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun FeaturedPreview() = GodsUnmadeTheme { FeaturedCharacterCard(previewHero, {}) }

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun CharacterCardPreview() = GodsUnmadeTheme { CharacterCard(previewBoss, {}) }

@Preview
@Composable
private fun CharacterAvatarPreview() = GodsUnmadeTheme { CharacterAvatar(previewBoss, height = 120.dp) }
