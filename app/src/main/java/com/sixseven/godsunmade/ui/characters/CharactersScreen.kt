package com.sixseven.godsunmade.ui.characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.ui.components.*
import com.sixseven.godsunmade.ui.theme.*

// lista de personajes: el protagonista arriba y luego los enemigos
@Composable
fun CharactersScreen(
    state: CharactersUiState,
    onRetry: () -> Unit,
    onCharacterClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxSize()) {
        AppTopBar("Personajes")
        when (state) {
            CharactersUiState.Loading -> LoadingView()
            is CharactersUiState.Error -> ErrorView(state.mensaje, onRetry)
            is CharactersUiState.Success -> LazyColumn(
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    LoreHeader(
                        "Registro de personajes", "Personajes",
                        "Un ser semihumano marcado por grietas divinas, capaz de encontrar y restaurar los Aspectos Divinos perdidos. Su recorrido comienza en Camino Roto, entre ruinas y santuarios.",
                    )
                }
                if (state.principal != null) {
                    item {
                        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            SectionTitle("Protagonista", subtitle = "Registro principal")
                            FeaturedCharacterCard(state.principal, onClick = { onCharacterClick(state.principal.id) })
                        }
                    }
                }
                item {
                    SectionTitle(
                        "Enemigos y guardianes",
                        Modifier.padding(horizontal = 16.dp),
                        subtitle = "Habitantes de las ruinas",
                    )
                }
                // cada enemigo es una tarjeta, al tocarla se abre su detalle
                items(state.enemigos, key = { it.id }) { p ->
                    CharacterCard(p, onClick = { onCharacterClick(p.id) }, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun CharactersPreview() = GodsUnmadeTheme {
    CharactersScreen(
        CharactersUiState.Success(
            null,
            listOf(Character("minotauro", "Minotauro", "002", "enemigo", null, "Enemigo terrestre pesado.", null, "minotauro")),
        ), {}, {},
    )
}
