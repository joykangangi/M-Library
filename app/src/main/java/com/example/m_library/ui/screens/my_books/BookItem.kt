package com.example.m_library.ui.screens.my_books


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.model.Book
import java.text.SimpleDateFormat
import java.util.*

// Always save constant variables in remember block or as file variables
// Format date as: Thu Jan 3, 2023
private val dateFormat = SimpleDateFormat("EEE MMM d, yyyy", Locale.ENGLISH)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit,
    book: Book
) {
    val onClick = remember {
        { onBookClicked(book) }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        onClick = onClick,
        content = {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    // try not to set fixed sizes for components
                    BookDetail(
                        modifier = Modifier.weight(1f),
                        bookTitle = book.title,
                        status = Book.choiceList[book.readStatus],
                        readByDate = book.readByDate
                    )
                    ProgressIndicator(
                        readChapters = book.currentChapter,
                        totChapters = book.totalChapters,
                        modifier = Modifier.padding(top = 8.dp, end = 6.dp)
                    )
                }
            )
        }
    )
}


//The first side of the card
@Composable
fun BookDetail(
    modifier: Modifier = Modifier,
    bookTitle: String,
    status: String,
    readByDate: Date
) {
    Column(
        modifier = modifier.padding(3.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start,
        content = {
            Text(
                text = stringResource(id = R.string.title, bookTitle),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(id = R.string.reading_status, status),
                style = MaterialTheme.typography.body1
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Icon(
                        imageVector = Icons.Filled.DateRange, contentDescription = stringResource(
                            id = R.string.deadline
                        ),
                        tint = Color.Gray
                    )
                    Text(
                        text = stringResource(
                            id = R.string.due_date,
                            dateFormat.format(readByDate)
                        ),
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray
                    )
                }
            )
        }
    )
}