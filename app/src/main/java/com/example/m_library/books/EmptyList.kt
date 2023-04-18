package com.example.m_library.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.m_library.R

@Composable
fun EmptyList(
    modifier: Modifier = Modifier,
) {
    val color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_books),
            contentDescription = stringResource(id = R.string.empty),
            colorFilter = ColorFilter.tint(color = color)
        )
        Text(
            text = stringResource(id = R.string.empty),
            style = MaterialTheme.typography.headlineSmall.copy(color = color),
        )
    }
}