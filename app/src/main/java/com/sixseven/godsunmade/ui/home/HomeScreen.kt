package com.sixseven.godsunmade.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.R
import com.sixseven.godsunmade.ui.components.AppTopBar
import com.sixseven.godsunmade.ui.components.ErrorView
import com.sixseven.godsunmade.ui.components.LoadingView
import com.sixseven.godsunmade.ui.components.SectionTitle
import com.sixseven.godsunmade.ui.navigation.Routes
import com.sixseven.godsunmade.ui.theme.*

/** Pantalla de inicio: recibe el estado y llama a onNavigate con la ruta de los accesos rápidos. */
@Composable
fun HomeScreen(state: HomeUiState, onNavigate: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        AppTopBar("Gods Unmade")
        when (state) {
            HomeUiState.Loading -> LoadingView()
            is HomeUiState.Error -> ErrorView(state.mensaje, onRetry = {})
            is HomeUiState.Success -> HomeContent(state, onNavigate)
        }
    }
}

@Composable
private fun HomeContent(state: HomeUiState.Success, onNavigate: (String) -> Unit) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Box(Modifier.fillMaxWidth().height(260.dp)) {
            Image(painterResource(R.drawable.portada), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Fondo))))
            Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                Text("SIXSEVENGAMES PRESENTA", style = MaterialTheme.typography.labelSmall, color = Bronce)
                Text("GODS", style = MaterialTheme.typography.displayMedium, color = Lino)
                Text("U N M A D E", style = MaterialTheme.typography.titleMedium, color = Bronce)
            }
        }
        Text(
            "«${state.fraseMaestra}»",
            Modifier.padding(horizontal = 24.dp).fillMaxWidth(),
            style = MaterialTheme.typography.titleMedium,
            fontStyle = FontStyle.Italic,
            color = BronceClaro,
            textAlign = TextAlign.Center,
        )
        Text(state.logline, Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.bodyLarge, color = LinoSuave)
        SectionTitle("Explora", Modifier.padding(horizontal = 16.dp))
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            QuickAccess("Personajes", "Protagonista, enemigos y guardianes", "☉") { onNavigate(Routes.CHARACTERS) }
            QuickAccess("Historia", "Capítulos del universo", "✦") { onNavigate(Routes.STORY) }
            QuickAccess("Top Global", "Ranking de jugadores", "♛") { onNavigate(Routes.LEADERBOARD) }
        }
        Text(
            "◇ ${state.avisoLore}",
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
            style = MaterialTheme.typography.labelMedium,
            color = Piedra,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun QuickAccess(title: String, subtitle: String, glyph: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Superficie),
        border = BorderStroke(1.dp, Bronce.copy(alpha = 0.6f)),
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(glyph, style = MaterialTheme.typography.headlineMedium, color = Bronce)
            Column(Modifier.weight(1f)) {
                Text(title.uppercase(), style = MaterialTheme.typography.titleMedium, color = Lino)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = LinoSuave)
            }
            Text("→", style = MaterialTheme.typography.titleLarge, color = Bronce)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun HomePreview() = GodsUnmadeTheme {
    HomeScreen(HomeUiState.Success("Ningún dios debería poder borrar a quienes se atreven a decirle que no", "Los dioses pierden fragmentos…", "Lore v1.0, contenido propuesto"), {})
}
