package com.example.m_library.bookdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.theme.MLibraryTheme
import com.example.m_library.app.theme.mediumPadding
import com.example.m_library.app.theme.smallPadding
import com.example.m_library.app.util.formatted
import com.example.m_library.app.util.formattedName
import com.example.m_library.app.widgets.BookProgress

@Composable
fun BookDetailScreen(
    book: Book,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            BookDetailAppBar(
                bookName = book.title,
                onBack = onBack,
                onDelete = onDelete,
                onEdit = onEdit,
            )
        },
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    BookProgress(
                        readChapters = book.currentChapter,
                        totalChapters = book.totalChapters,
                        textStyle = MaterialTheme.typography.headlineSmall,
                        radius = 50.dp,
                    )
                    Spacer(modifier = Modifier.height(mediumPadding()))
                    Text(
                        text = "Written by ${book.author}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontStyle = FontStyle.Italic,
                        maxLines = 1,
                    )
                    Text(
                        modifier = Modifier.padding(
                            vertical = mediumPadding(),
                        ),
                        text = stringResource(id = R.string.info),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    BookDetailItem(
                        title = stringResource(id = R.string.reading_st),
                        details = book.readStatus.formattedName,
                    )
                    Divider()
                    BookDetailItem(
                        title = stringResource(id = R.string.finishby),
                        details = book.readByDate.formatted
                    )
                    Divider()
                    BookDetailItem(
                        title = stringResource(id = R.string.currentChp),
                        details = book.currentChapter.toString()
                    )
                    Divider()
                    BookDetailItem(
                        title = stringResource(id = R.string.totChap),
                        details = book.totalChapters.toString()
                    )
                }
            )
        }
    )
}

@Composable
private fun BookDetailItem(
    title: String,
    details: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = smallPadding(),
                horizontal = mediumPadding(),
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = details,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    )
}

@Preview
@Composable
private fun BookDetailScreenPreview() = MLibraryTheme {
    BookDetailScreen(
        book = Book(
            title = "Title",
            author = "Joy",
            currentChapter = 10,
            totalChapters = 20,
        ),
        onBack = {},
        onEdit = {},
        onDelete = {},
    )
}