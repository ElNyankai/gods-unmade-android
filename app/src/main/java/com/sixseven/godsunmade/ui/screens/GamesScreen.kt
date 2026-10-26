package com.sixseven.godsunmade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sixseven.godsunmade.ui.components.BodyText
import com.sixseven.godsunmade.ui.components.GodsButton
import com.sixseven.godsunmade.ui.components.GodsCard
import com.sixseven.godsunmade.ui.components.GodsChip
import com.sixseven.godsunmade.ui.components.GodsImage
import com.sixseven.godsunmade.ui.components.GodsTable
import com.sixseven.godsunmade.ui.components.OrnamentalDivider
import com.sixseven.godsunmade.ui.components.ScreenTitle
import com.sixseven.godsunmade.ui.components.SubTitle
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.viewmodel.GamesViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GamesScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    vm: GamesViewModel = viewModel()
) {
    val game by vm.game.collectAsState()
    val controls by vm.controls.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 20.dp, end = 24.dp, top = 20.dp, bottom = 24.dp)
    ) {
        item {
            Column {
                ScreenTitle("Juego")
                OrnamentalDivider(modifier = Modifier.padding(bottom = 12.dp))
            }
        }

        game?.let { g ->
            item {
                GodsCard(modifier = Modifier.fillMaxWidth()) {
                    Column {
                        GodsImage(
                            url = null,
                            glyph = "✦",
                            modifier = Modifier.fillMaxWidth().height(160.dp)
                        )
                        Column(modifier = Modifier.padding(16.dp)) {
                            SubTitle(text = g.titulo, color = TextPrimary)
                            Spacer(modifier = Modifier.height(10.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                GodsChip(text = g.genero, selected = true)
                                GodsChip(text = g.plataforma)
                                GodsChip(text = "Estado: ${g.estado}")
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            BodyText(text = g.descripcion)
                            Spacer(modifier = Modifier.height(12.dp))
                            SubTitle(text = "Objetivo")
                            BodyText(text = g.objetivo)
                            Spacer(modifier = Modifier.height(12.dp))
                            BodyText(text = "Esta app móvil es complementaria al juego, que se ejecuta en PC.")
                            Spacer(modifier = Modifier.height(16.dp))
                            GodsButton(text = "LEER LA HISTORIA", onClick = { onNavigate("story") })
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    OrnamentalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    SubTitle(text = "Cómo se juega")
                    Spacer(modifier = Modifier.height(8.dp))
                    BodyText(text = g.comoSeJuega)
                    Spacer(modifier = Modifier.height(16.dp))
                    GodsTable(
                        headerLeft = "Función",
                        headerRight = "Acción",
                        rows = controls.map { it.funcion to it.accion }
                    )
                }
            }

            item {
                Column {
                    OrnamentalDivider(modifier = Modifier.padding(vertical = 16.dp))
                    SubTitle(text = "Reglas")
                    Spacer(modifier = Modifier.height(8.dp))
                    g.reglas.forEach { rule ->
                        Row(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text(
                                text = "◆",
                                color = Gold,
                                fontFamily = GodsMono,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 6.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            BodyText(text = rule, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}