package com.sixseven.godsunmade.ui.leaderboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.data.model.Player
import com.sixseven.godsunmade.ui.components.*
import com.sixseven.godsunmade.ui.theme.GodsUnmadeTheme

@Composable
fun LeaderboardScreen(state: LeaderboardUiState, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        AppTopBar("Top Global")
        when (state) {
            LeaderboardUiState.Loading -> LoadingView()
            is LeaderboardUiState.Error -> ErrorView(state.mensaje, onRetry)
            is LeaderboardUiState.Success -> {
                if (state.podio.isEmpty()) {
                    EmptyView("Aún no hay jugadores en el ranking")
                } else LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    item { Podio(state.podio) }
                    item { SectionTitle("Clasificación", Modifier.padding(top = 12.dp)) }
                    items(state.resto, key = { it.posicion }) { LeaderboardRow(it) }
                }
            }
        }
    }
}

@Composable
private fun Podio(podio: List<Player>) {
    // Orden visual: 2º, 1º, 3º
    val orden = listOfNotNull(podio.getOrNull(1), podio.getOrNull(0), podio.getOrNull(2))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.Bottom) {
        orden.forEach { PodiumItem(it, Modifier.weight(1f)) }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun LeaderboardScreenPreview() = GodsUnmadeTheme {
    LeaderboardScreen(
        LeaderboardUiState.Success(
            listOf(Player(1, "Nova", 98000), Player(2, "Rook", 80000), Player(3, "Lyra", 70000)),
            listOf(Player(4, "Zed", 60000)),
        ), {},
    )
}
