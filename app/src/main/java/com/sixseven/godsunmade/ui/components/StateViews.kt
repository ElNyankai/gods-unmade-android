package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.ui.theme.*

// circulo dando vueltas mientras esperamos los datos
@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Bronce)
    }
}

// mensaje de error con boton para intentar de nuevo
@Composable
fun ErrorView(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("✦", style = MaterialTheme.typography.displayMedium, color = Arcilla)
        Spacer(Modifier.height(8.dp))
        Text("Algo salió mal", style = MaterialTheme.typography.titleMedium, color = Lino)
        Text(message, style = MaterialTheme.typography.bodyMedium, color = Piedra, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRetry, colors = ButtonDefaults.buttonColors(containerColor = Arcilla, contentColor = Lino)) {
            Text("REINTENTAR", style = MaterialTheme.typography.labelLarge)
        }
    }
}

// aviso cuando la lista no tiene elementos
@Composable
fun EmptyView(message: String, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("◇", style = MaterialTheme.typography.displayMedium, color = Bronce)
        Spacer(Modifier.height(8.dp))
        Text(message, style = MaterialTheme.typography.bodyMedium, color = LinoSuave, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun LoadingPreview() = GodsUnmadeTheme { LoadingView() }

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun ErrorPreview() = GodsUnmadeTheme { ErrorView("No se pudo leer el archivo", {}) }

@Preview(showBackground = true, backgroundColor = 0xFF171C2A)
@Composable
private fun EmptyPreview() = GodsUnmadeTheme { EmptyView("Sin resultados") }
