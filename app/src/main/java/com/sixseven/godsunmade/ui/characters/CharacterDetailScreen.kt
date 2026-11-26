package com.sixseven.godsunmade.ui.characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.data.model.Character
import com.sixseven.godsunmade.ui.components.*
import com.sixseven.godsunmade.ui.theme.*

// muestra imagen y datos del personaje elegido
@Composable
fun CharacterDetailScreen(
    state: CharacterDetailUiState,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier.fillMaxSize()) {
        // el titulo cambia segun si ya cargo el personaje
        AppTopBar((state as? CharacterDetailUiState.Success)?.personaje?.nombre ?: "Personaje", onBack = onBack)
        when (state) {
            CharacterDetailUiState.Loading -> LoadingView()
            is CharacterDetailUiState.Error -> ErrorView(state.mensaje, onRetry)
            is CharacterDetailUiState.Success -> {
                val p = state.personaje
                Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                    CharacterAvatar(p, height = 340.dp)
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        RegistroLabel(p.registro)
                        Text(p.nombre.uppercase(), style = MaterialTheme.typography.headlineMedium, color = Marfil)
                        if (p.etiqueta != null) TagLabel(p.etiqueta)
                        OrnamentDivider(Modifier.padding(vertical = 4.dp))
                        Text(p.descripcion, style = MaterialTheme.typography.bodyLarge, color = Apagado)
                        if (p.objetivo != null) ObjectiveBlock(p.objetivo)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF100C12)
@Composable
private fun CharacterDetailPreview() = GodsUnmadeTheme {
    val c = Character("custodio", "Custodio", "005", "jefe", "JEFE DE LA DEMO", "Guardián principal de la demo.", null, "custodio")
    CharacterDetailScreen(CharacterDetailUiState.Success(c), {}, {})
}
