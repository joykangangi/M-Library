package com.example.m_library.app.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(
    text: String?,
    modifier: Modifier = Modifier,
) {
    if (text?.isNotBlank() == true) Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.error,
        fontSize = 12.sp
    )
}