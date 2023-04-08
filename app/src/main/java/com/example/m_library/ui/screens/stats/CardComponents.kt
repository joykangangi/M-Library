package com.example.m_library.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.m_library.R


@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.h6.fontSize
    )
}

@Composable
fun CardNumber(number: String) {
    Text(
        text = number,
        fontSize = MaterialTheme.typography.body1.fontSize,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(
                shape = CircleShape,
                color = MaterialTheme.colors.secondary
            )
            .padding(horizontal = 5.dp)
    )
}


@Composable
fun CardArrow(cardArrowClick: () -> Unit, arrowDegrees: Float) {
    IconButton(onClick = cardArrowClick) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = stringResource(id = R.string.expand_icon),
            modifier = Modifier
                .rotate(arrowDegrees)
                .size(30.dp))
    }
}

