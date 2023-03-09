package com.example.m_library.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CustomBottomNavigation(
    currentScreenId: String,
    onItemSelected: (BottomScreen) -> Unit
) {
    val items: List<BottomScreen> = BottomScreen.Items.list

    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEach { item ->
            BottomNavItem(item = item, isSelected = item.id == currentScreenId) {
            // onClick =
            onItemSelected(it)
            }
        }
    }
}

@Composable
fun BottomNavItem(
    item: BottomScreen,
    isSelected: Boolean,
    onClick: (BottomScreen) -> Unit
) {
    val background =
        if (isSelected) MaterialTheme.colors.background.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = background)
            .clickable(onClick = { onClick(item) })
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = item.icon, contentDescription = item.title, tint = contentColor)
            AnimatedVisibility(visible = isSelected) {
                Text(text = item.title, color = contentColor)
            }

        }

    }
}


@Preview
@Composable
fun BottPrev1() {
    CustomBottomNavigation(currentScreenId = BottomScreen.MyBooks.id, onItemSelected = {})
}

@Preview
@Composable
fun BottPrev1Item() {
    BottomNavItem(item = BottomScreen.MyBooks, isSelected = true, onClick = {})
}

@Preview
@Composable
fun BottPrev2() {
    CustomBottomNavigation(currentScreenId = BottomScreen.AddBook.id, onItemSelected = {})
}

@Preview
@Composable
fun BottPrev3() {
    CustomBottomNavigation(currentScreenId = BottomScreen.NewWord.id, onItemSelected = {})
}