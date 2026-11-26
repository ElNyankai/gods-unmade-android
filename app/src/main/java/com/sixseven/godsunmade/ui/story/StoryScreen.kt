package com.sixseven.godsunmade.ui.story

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sixseven.godsunmade.R
import com.sixseven.godsunmade.ui.components.*
import com.sixseven.godsunmade.ui.theme.*

// pantalla de historia: cabecera, destacados y secciones
@Composable
fun StoryScreen(
    state: StoryUiState,
    onRetry: () -> Unit,
    onChapterClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxSize()) {
        AppTopBar("Historia")
        when (state) {
            StoryUiState.Loading -> LoadingView()
            is StoryUiState.Error -> ErrorView(state.mensaje, onRetry)
            is StoryUiState.Success -> LazyColumn(
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item { StoryHero() }
                if (state.destacados.isNotEmpty()) {
                    item { SectionTitle("Destacados", Modifier.padding(horizontal = 16.dp)) }
                    item {
                        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(state.destacados, key = { "d_${it.id}" }) { c ->
                                StoryChapterCard(c, { onChapterClick(c.id) }, Modifier.width(260.dp), height = 160.dp, showSummary = false)
                            }
                        }
                    }
                }
                state.secciones.forEach { (seccion, capitulos) ->
                    item(key = "s_${seccion.key}") { StorySectionHeader(seccion, Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)) }
                    items(capitulos, key = { it.id }) { c ->
                        StoryChapterCard(c, { onChapterClick(c.id) }, Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun StoryHero() {
    Box(Modifier.fillMaxWidth().height(240.dp)) {
        Image(painterResource(R.drawable.historia), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Sombra.copy(alpha = 0.3f), Sombra))))
        Column(Modifier.align(Alignment.BottomStart).padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("LOS RELATOS DEL MUNDO", style = MaterialTheme.typography.labelMedium, color = Oro)
            Text("HISTORIA", style = MaterialTheme.typography.displayMedium.copy(brush = GradienteTitulo))
            OrnamentDivider(Modifier.width(120.dp))
            Text(
                "Los dioses pierden fragmentos de su identidad. Explora qué se oculta detrás de la fractura.",
                style = MaterialTheme.typography.bodyMedium, color = Apagado,
            )
        }
    }
}

// si el capitulo tiene spoilers, primero sale la advertencia
@Composable
fun ChapterDetailScreen(
    state: ChapterUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    onAcceptSpoiler: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxSize()) {
        AppTopBar((state as? ChapterUiState.Success)?.capitulo?.titulo ?: "Capítulo", onBack = onBack)
        when (state) {
            ChapterUiState.Loading -> LoadingView()
            is ChapterUiState.Error -> ErrorView(state.mensaje, onRetry)
            is ChapterUiState.Success ->
                if (!state.spoilerAceptado) SpoilerWarning(onContinue = onAcceptSpoiler, onBack = onBack)
                else ChapterBody(state)
        }
    }
}

// texto del capitulo, la cabecera se desvanece al bajar
@Composable
private fun ChapterBody(state: ChapterUiState.Success) {
    val c = state.capitulo
    val scroll = rememberScrollState()
    val headerPx = with(LocalDensity.current) { 280.dp.toPx() }
    Column(Modifier.fillMaxSize().verticalScroll(scroll)) {
        Box(
            Modifier.fillMaxWidth().height(280.dp).graphicsLayer {
                translationY = scroll.value * 0.5f
                alpha = (1f - scroll.value / headerPx).coerceIn(0f, 1f)
            },
        ) {
            ChapterVisual(c.imagen, c.arte, Modifier.fillMaxSize())
            Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Sombra))))
            Column(Modifier.align(Alignment.BottomStart).padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("${c.section.label.uppercase()} · CAP. %02d".format(c.orden), style = MaterialTheme.typography.labelMedium, color = Oro)
                Text(c.titulo, style = MaterialTheme.typography.headlineMedium, color = Marfil)
                if (c.tieneSpoilers) SpoilerBadge()
            }
        }
        Column(Modifier.padding(horizontal = 20.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
            // el resumen va en una barra roja a la izquierda
            Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
                Box(Modifier.width(4.dp).fillMaxHeight().background(Peligro))
                Text(
                    c.resumen,
                    Modifier.padding(start = 14.dp),
                    style = MaterialTheme.typography.titleSmall.copy(lineHeight = 24.sp),
                    color = Oro,
                )
            }
            OrnamentDivider()
            // el primer parrafo lleva letra grande y cada dos un separador
            c.contenido.forEachIndexed { i, parrafo ->
                if (i == 0) DropCapParagraph(parrafo)
                else Text(parrafo, style = MaterialTheme.typography.bodyLarge, color = Marfil)
                if (i % 2 == 1 && i < c.contenido.lastIndex) OrnamentDivider(Modifier.padding(vertical = 4.dp))
            }
            OrnamentDivider(Modifier.padding(top = 8.dp, bottom = 24.dp))
        }
    }
}

// parrafo con la primera letra grande
@Composable
private fun DropCapParagraph(text: String) {
    if (text.isEmpty()) return
    Text(
        buildAnnotatedString {
            withStyle(SpanStyle(fontSize = 44.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Black, color = Oro)) {
                append(text.first())
            }
            append(text.drop(1))
        },
        style = MaterialTheme.typography.bodyLarge,
        color = Marfil,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF100C12)
@Composable
private fun StoryPreview() = GodsUnmadeTheme { StoryScreen(StoryUiState.Success(emptyList(), emptyList()), {}, {}) }
