package com.sixseven.godsunmade.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sixseven.godsunmade.ui.components.BodyText
import com.sixseven.godsunmade.ui.components.OrnamentalDivider
import com.sixseven.godsunmade.ui.components.ScreenTitle
import com.sixseven.godsunmade.ui.components.SubTitle
import com.sixseven.godsunmade.viewmodel.StoryViewModel

@Composable
fun StoryScreen(
    modifier: Modifier = Modifier,
    vm: StoryViewModel = viewModel()
) {
    val chapters by vm.chapters.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp)
    ) {
        item {
            Column {
                ScreenTitle("Historia")
                OrnamentalDivider(modifier = Modifier.padding(bottom = 16.dp))
            }
        }
        items(chapters, key = { it.id }) { chapter ->
            Column {
                SubTitle(text = chapter.titulo)
                Spacer(modifier = Modifier.height(10.dp))
                chapter.texto.split("\n\n").forEach { paragraph ->
                    BodyText(text = paragraph)
                    Spacer(modifier = Modifier.height(12.dp))
                }
                OrnamentalDivider(modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    }
}