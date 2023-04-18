package com.example.m_library.books


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.theme.mediumPadding
import com.example.m_library.app.theme.smallPadding
import com.example.m_library.app.util.formatted
import com.example.m_library.app.util.formattedName
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
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
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(mediumPadding()),
        elevation = CardDefaults.cardElevation(defaultElevation = smallPadding()),
        onClick = onClick,
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(smallPadding()),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    BookDetail(
                        modifier = Modifier.weight(1f),
                        bookTitle = book.title,
                        status = book.readStatus.formattedName,
                        readByDate = book.readByDate
                    )
                    ProgressIndicator(
                        readChapters = book.currentChapter,
                        totalChapters = book.totalChapters,
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
    readByDate: LocalDate
) {
    val color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    Column(
        modifier = modifier.padding(3.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start,
        content = {
            Text(
                text = stringResource(id = R.string.title, bookTitle),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(id = R.string.reading_status, status),
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = stringResource(
                            id = R.string.deadline
                        ),
                        tint = color,
                    )
                    Spacer(modifier = Modifier.width(smallPadding()))
                    Text(
                        text = stringResource(
                            id = R.string.due_date,
                            readByDate.formatted,
                        ),
                        style = MaterialTheme.typography.labelSmall,
                        color = color
                    )
                }
            )
        }
    )
}