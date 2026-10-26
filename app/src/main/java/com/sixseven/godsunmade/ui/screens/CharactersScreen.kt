package com.sixseven.godsunmade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sixseven.godsunmade.data.Character
import com.sixseven.godsunmade.ui.components.BodyText
import com.sixseven.godsunmade.ui.components.CharacterArt
import com.sixseven.godsunmade.ui.components.GodsButton
import com.sixseven.godsunmade.ui.components.GodsCard
import com.sixseven.godsunmade.ui.components.GodsChip
import com.sixseven.godsunmade.ui.components.LabelText
import com.sixseven.godsunmade.ui.components.OrnamentalDivider
import com.sixseven.godsunmade.ui.components.ScreenTitle
import com.sixseven.godsunmade.ui.components.SubTitle
import com.sixseven.godsunmade.ui.theme.Gold
import com.sixseven.godsunmade.ui.theme.GodsMono
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.ui.theme.TextSecondary
import com.sixseven.godsunmade.viewmodel.CharacterDetailViewModel
import com.sixseven.godsunmade.viewmodel.CharactersViewModel

@Composable
fun CharactersScreen(
    onCharacterClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    vm: CharactersViewModel = viewModel()
) {
    val list by vm.characters.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 20.dp, top = 20.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Column {
                ScreenTitle("Personajes")
                OrnamentalDivider()
            }
        }
        items(list, key = { it.id }) { c ->
            CharacterCard(character = c, onClick = { onCharacterClick(c.id) })
        }
    }
}

@Composable
private fun CharacterCard(character: Character, onClick: () -> Unit) {
    GodsCard(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Column {
            CharacterArt(
                drawable = character.drawable,
                categoria = character.categoria,
                modifier = Modifier.fillMaxWidth().height(110.dp)
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = character.nombre.uppercase(),
                    color = TextPrimary,
                    fontFamily = GodsMono,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                LabelText(
                    text = character.rol,
                    color = Gold,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = character.descripcion,
                    color = TextSecondary,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun CharacterDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    vm: CharacterDetailViewModel = viewModel()
) {
    val character by vm.character.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 20.dp, end = 24.dp, top = 20.dp, bottom = 24.dp)
    ) {
        val c = character
        if (c == null) {
            BodyText(text = "Cargando…")
        } else {
            GodsCard(modifier = Modifier.fillMaxWidth()) {
                CharacterArt(
                    drawable = c.drawable,
                    categoria = c.categoria,
                    modifier = Modifier.fillMaxWidth().height(220.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            ScreenTitle(c.nombre)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GodsChip(text = c.categoria, selected = true)
                LabelText(text = c.rol, modifier = Modifier.weight(1f))
            }
            OrnamentalDivider(modifier = Modifier.padding(vertical = 12.dp))
            SubTitle(text = "Descripción")
            BodyText(text = c.descripcion)
            Spacer(modifier = Modifier.height(16.dp))
            SubTitle(text = "Rol en el juego")
            BodyText(text = c.rolEnJuego)
            Spacer(modifier = Modifier.height(24.dp))
            GodsButton(text = "VOLVER", onClick = onBack)
        }
    }
}