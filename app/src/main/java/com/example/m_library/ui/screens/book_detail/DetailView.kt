package com.example.m_library.ui.screens.book_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.ui.screens.my_books.ProgressIndicator
import com.example.m_library.viewmodel.BookViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookDetail(
    onBackClicked: () -> Unit,
    bookViewModel: BookViewModel
) {
    // Format date as: Thu Jan 3, 2023
    val dateFormat = SimpleDateFormat("EEE MMM d, yyyy", Locale.ENGLISH)
    val book = bookViewModel.bookDetailState.value.book

    Log.i("bookdetail title", "${bookViewModel.bookDetailState}")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, top = 8.dp, end = 12.dp)
    ) {
        if (book != null) {
            DetailTopAppBar(bookTitle = book.title, onBackClicked = onBackClicked)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MoreDetail(title = stringResource(id = R.string.author), details = book.author)
                ProgressIndicator(
                    readChapters = book.currentChapter,
                    totChapters = book.totalChapters,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            MoreDetail(
                title = stringResource(id = R.string.reading_st),
                details = book.readStatus.name
            )
            book.readByDate?.let {
                dateFormat.format(
                    it
                )
            }?.let { MoreDetail(title = stringResource(id = R.string.finishby), details = it) }

            MoreDetail(
                title = stringResource(id = R.string.currentChp),
                details = book.currentChapter
            )
            MoreDetail(title = stringResource(id = R.string.totChap), details = book.totalChapters)

        }
    }
}


@Composable
fun MoreDetail(
    title: String,
    details: Any,
    titleStyle: TextStyle = MaterialTheme.typography.subtitle1,
    detailStyle: TextStyle = MaterialTheme.typography.body1
) {
    Row {
        Text(text = "$title: ", style = titleStyle)
        Text(text = details.toString(), style = detailStyle)
        Spacer(modifier = Modifier.height(5.dp))
    }
}