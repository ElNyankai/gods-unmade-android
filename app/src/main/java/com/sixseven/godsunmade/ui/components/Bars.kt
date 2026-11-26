package com.sixseven.godsunmade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sixseven.godsunmade.ui.theme.*

// barra de arriba con el titulo, y flecha para volver si nos pasan onBack
@Composable
fun AppTopBar(title: String, modifier: Modifier = Modifier, onBack: (() -> Unit)? = null) {
    Column(modifier.fillMaxWidth().background(Ciruela)) {
        Box(Modifier.fillMaxWidth().height(3.dp).background(Peligro))
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Marfil)
                }
            } else {
                Box(
                    Modifier.size(34.dp).background(OxidoOscuro).border(2.dp, Cobre),
                    contentAlignment = Alignment.Center,
                ) { Text("✦", color = Marfil, style = MaterialTheme.typography.titleMedium) }
            }
            Text(
                title.uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = Marfil,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Box(Modifier.fillMaxWidth().height(2.dp).background(Cobre))
    }
}

// un boton de la barra de abajo: ruta, texto e icono
data class BottomDestination(val route: String, val label: String, val icon: ImageVector)

// barra de abajo, marca la pestana actual y navega al tocar
@Composable
fun AppBottomBar(
    destinations: List<BottomDestination>,
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier, containerColor = Ciruela, contentColor = Marfil) {
        destinations.forEach { d ->
            NavigationBarItem(
                selected = currentRoute == d.route,
                onClick = { onNavigate(d.route) },
                icon = { Icon(d.icon, contentDescription = d.label) },
                label = { Text(d.label.uppercase(), style = MaterialTheme.typography.labelSmall, maxLines = 1) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Oro,
                    selectedTextColor = Oro,
                    indicatorColor = OxidoOscuro,
                    unselectedIconColor = Apagado,
                    unselectedTextColor = Apagado,
                ),
            )
        }
    }
}

// titulo de seccion con linea dorada debajo
@Composable
fun SectionTitle(text: String, modifier: Modifier = Modifier, subtitle: String? = null) {
    Column(modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("◆ ${text.uppercase()}", style = MaterialTheme.typography.titleMedium, color = Oro)
        if (subtitle != null) Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Apagado)
        Box(Modifier.fillMaxWidth().height(1.dp).background(Cobre.copy(alpha = 0.5f)))
    }
}

@Preview
@Composable
private fun BarsPreview() {
    GodsUnmadeTheme {
        Column {
            AppTopBar("Personajes", onBack = {})
            SectionTitle("Fundamentos", subtitle = "La verdad del universo")
            AppBottomBar(
                listOf(
                    BottomDestination("a", "Inicio", Icons.Default.Home),
                    BottomDestination("b", "Historia", Icons.AutoMirrored.Filled.List),
                    BottomDestination("c", "Personajes", Icons.Default.Person),
                    BottomDestination("d", "Top", Icons.Default.Star),
                ), "a", {},
            )
        }
    }
}
