package com.example.m_library.ui.screens.add_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ReadingStatusRadio(
    options: List<String>,
    selectedIndex: Int?,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(options) { index: Int, item: String ->
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                RadioButton(
                    selected = (index == selectedIndex),
                    onClick = { onSelected(index)},
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colors.primary,
                        unselectedColor = MaterialTheme.colors.onBackground
                    )
                )
                Text(text = item, style = MaterialTheme.typography.body1)
            }
        }
    }
}