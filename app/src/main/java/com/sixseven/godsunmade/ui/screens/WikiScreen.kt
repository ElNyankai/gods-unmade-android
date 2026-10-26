package com.sixseven.godsunmade.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sixseven.godsunmade.ui.components.BodyText
import com.sixseven.godsunmade.ui.components.GodsCard
import com.sixseven.godsunmade.ui.components.GodsChip
import com.sixseven.godsunmade.ui.components.GodsFooter
import com.sixseven.godsunmade.ui.components.GodsTextField
import com.sixseven.godsunmade.ui.components.LabelText
import com.sixseven.godsunmade.ui.components.OrnamentalDivider
import com.sixseven.godsunmade.ui.components.ScreenTitle
import com.sixseven.godsunmade.ui.components.SubTitle
import com.sixseven.godsunmade.ui.theme.TextPrimary
import com.sixseven.godsunmade.viewmodel.WikiViewModel

@Composable
fun WikiScreen(
    modifier: Modifier = Modifier,
    vm: WikiViewModel = viewModel()
) {
    val query by vm.query.collectAsState()
    val category by vm.category.collectAsState()
    val grouped by vm.grouped.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Column(modifier = Modifier.padding(start = 20.dp, end = 24.dp, top = 20.dp)) {
                ScreenTitle("Wiki")
                OrnamentalDivider()
                GodsTextField(
                    value = query,
                    onValueChange = vm::onQueryChange,
                    label = "Buscar en la wiki"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(end = 4.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GodsChip(
                        text = "Todas",
                        selected = category.isBlank(),
                        onClick = { vm.onCategoryChange("") }
                    )
                    vm.categories.forEach { c ->
                        GodsChip(
                            text = c,
                            selected = category == c,
                            onClick = { vm.onCategoryChange(c) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        grouped.forEach { (cat, entries) ->
            item(key = "cat_$cat") {
                SubTitle(
                    text = cat,
                    modifier = Modifier.padding(start = 20.dp, end = 24.dp, top = 12.dp, bottom = 8.dp)
                )
            }
            entries.forEach { entry ->
                item(key = entry.id) {
                    GodsCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 24.dp, top = 6.dp, bottom = 10.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            LabelText(text = entry.categoria)
                            SubTitle(text = entry.titulo, color = TextPrimary)
                            Spacer(modifier = Modifier.height(4.dp))
                            BodyText(text = entry.contenido)
                        }
                    }
                }
            }
        }

        if (grouped.isEmpty()) {
            item {
                BodyText(text = "Sin resultados.", modifier = Modifier.padding(24.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            GodsFooter()
        }
    }
}