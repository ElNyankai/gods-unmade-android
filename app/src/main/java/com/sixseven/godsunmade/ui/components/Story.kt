package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.data.model.StoryChapter
import com.sixseven.godsunmade.data.model.StorySection
import com.sixseven.godsunmade.ui.theme.*

// etiqueta roja que avisa que hay spoilers
@Composable
fun SpoilerBadge(modifier: Modifier = Modifier) {
    Text(
        "SPOILERS",
        modifier = modifier.background(Arcilla, RoundedCornerShape(2.dp)).padding(horizontal = 6.dp, vertical = 2.dp),
        style = MaterialTheme.typography.labelSmall,
        color = Lino,
    )
}

// pantalla de aviso antes de leer, deja seguir o volver
@Composable
fun SpoilerWarning(onContinue: () -> Unit, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(Icons.Default.Warning, contentDescription = null, tint = Bronce, modifier = Modifier.size(48.dp))
        Spacer(Modifier.height(12.dp))
        Text("ADVERTENCIA DE SPOILERS", style = MaterialTheme.typography.titleMedium, color = Lino, textAlign = TextAlign.Center)
        Spacer(Modifier.height(8.dp))
        Text(
            "Este capítulo revela giros importantes de la historia. ¿Quieres continuar?",
            style = MaterialTheme.typography.bodyMedium,
            color = LinoSuave,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = onContinue, colors = ButtonDefaults.buttonColors(containerColor = Arcilla, contentColor = Lino)) {
            Text("LEER DE TODOS MODOS", style = MaterialTheme.typography.labelLarge)
        }
        TextButton(onClick = onBack) { Text("VOLVER", style = MaterialTheme.typography.labelLarge, color = Bronce) }
    }
}

// degradado oscuro para que se lea el texto sobre la imagen
private val darkFade = Brush.verticalGradient(
    listOf(Sombra.copy(alpha = 0.15f), Sombra.copy(alpha = 0.55f), Sombra.copy(alpha = 0.95f)),
)

// tarjeta del capitulo con imagen de fondo y titulo encima
@Composable
fun StoryChapterCard(
    chapter: StoryChapter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 190.dp,
    showSummary: Boolean = true,
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Superficie),
        border = BorderStroke(1.dp, Bronce.copy(alpha = 0.8f)),
    ) {
        Box(Modifier.fillMaxSize()) {
            ChapterVisual(chapter.imagen, chapter.arte, Modifier.fillMaxSize())
            Box(Modifier.fillMaxSize().background(darkFade))
            Row(Modifier.align(Alignment.TopStart).padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "CAP. %02d".format(chapter.orden),
                    Modifier.background(Sombra.copy(alpha = 0.7f)).border(1.dp, Bronce).padding(horizontal = 8.dp, vertical = 3.dp),
                    style = MaterialTheme.typography.labelSmall, color = Bronce,
                )
                if (chapter.tieneSpoilers) SpoilerBadge()
            }
            Column(Modifier.align(Alignment.BottomStart).padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(chapter.titulo, style = MaterialTheme.typography.titleMedium, color = Lino, maxLines = 2, overflow = TextOverflow.Ellipsis)
                if (showSummary) Text(chapter.resumen, style = MaterialTheme.typography.bodySmall, color = LinoSuave, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

// encabezado de seccion con dibujo a la izquierda
@Composable
fun StorySectionHeader(section: StorySection, modifier: Modifier = Modifier) {
    val arte = when (section) {
        StorySection.FUNDAMENTOS -> "balanza"
        StorySection.CAMPANA -> "camino"
        StorySection.DESENLACE -> "concordancia"
        StorySection.MUNDO -> "escudo"
    }
    Row(
        modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp)).border(1.dp, Bronce, RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ChapterArt(arte, Modifier.size(76.dp))
        Column(Modifier.padding(horizontal = 14.dp).weight(1f)) {
            Text("◆ ${section.label.uppercase()}", style = MaterialTheme.typography.titleMedium, color = Bronce)
            Text(section.descripcion, style = MaterialTheme.typography.bodySmall, color = LinoSuave)
        }
    }
}

private val previewChapter = StoryChapter(
    id = "acto_2", orden = 10, titulo = "Acto II: El mar que no sabe retirarse",
    resumen = "En la costa, Poseidón enfrenta el costo de la campaña.",
    contenido = emptyList(), tieneSpoilers = true, seccion = "campana", arte = "mar",
)

@Preview
@Composable
private fun SpoilerBadgePreview() = GodsUnmadeTheme { SpoilerBadge() }

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun SpoilerWarningPreview() = GodsUnmadeTheme { SpoilerWarning({}, {}) }

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun StoryChapterCardPreview() = GodsUnmadeTheme {
    Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        StoryChapterCard(previewChapter, {})
        StorySectionHeader(StorySection.CAMPANA)
    }
}
