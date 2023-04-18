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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.theme.MLibraryTheme
import com.example.m_library.app.theme.smallPadding
import com.example.m_library.app.util.formattedName
import com.example.m_library.app.widgets.BookProgress
import java.time.format.DateTimeFormatter

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

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = book.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.headlineSmall,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = stringResource(id = R.string.info),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    MoreDetail(
                        title = stringResource(id = R.string.reading_st),
                        details = book.readStatus.formattedName
                    )
                    MoreDetail(
                        title = stringResource(id = R.string.finishby),
                        details = book.readByDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
                    )
                    MoreDetail(
                        title = stringResource(id = R.string.currentChp),
                        details = book.currentChapter.toString()
                    )
                    MoreDetail(
                        title = stringResource(id = R.string.totChap),
                        details = book.totalChapters.toString()
                    )
                }
            )
        }
    )
}

@Composable
fun MoreDetail(
    title: String,
    details: String,
    modifier: Modifier = Modifier,
    spaced: Dp = smallPadding(),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spaced),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(smallPadding() / 2),
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
            Divider()
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