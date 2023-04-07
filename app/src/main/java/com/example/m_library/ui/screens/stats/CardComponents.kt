package com.example.m_library.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.model.Book


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

@Composable
fun BookNames(books: List<Book>) {

        LazyColumn(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
            items(books) { book: Book ->
                Text(text = book.title)
                Divider()
            }
        }
    }
