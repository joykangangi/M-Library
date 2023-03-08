package com.example.m_library.ui.screens.my_books


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.R
import com.example.m_library.data.local.Book

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    onBookClicked: () -> Unit,
    book: Book
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            }

            }
        }

    }


@Composable
fun Component1(
    bookTitle: String,
    status: String,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.title, bookTitle),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = stringResource(id = R.string.reading_status, status), style = MaterialTheme.typography.body1)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.DateRange, contentDescription = stringResource(
                    id = R.string.deadline
                )
            )

            Text(
                text = stringResource(id = R.string.due_date, book.readByDate),
                style = MaterialTheme.typography.caption
            )
        }
    }
}