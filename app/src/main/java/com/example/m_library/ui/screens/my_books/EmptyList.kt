package com.example.m_library.ui.screens.my_books

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.m_library.R

@Composable
fun EmptyList(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_books),
            contentDescription = stringResource(id = R.string.empty),
            colorFilter = ColorFilter.tint(color = Color.Gray)
        )
        Text(
            text = stringResource(id = R.string.empty),
            fontSize = 22.sp,
            style = MaterialTheme.typography.h3,
            color = Color.Gray
        )
    }
}