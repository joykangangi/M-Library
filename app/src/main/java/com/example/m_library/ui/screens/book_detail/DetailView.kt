package com.example.m_library.ui.screens.book_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.R
import com.example.m_library.model.Book
import com.example.m_library.ui.screens.add_book.components.EditBookEvents
import com.example.m_library.ui.screens.my_books.ProgressIndicator
import com.example.m_library.util.Constants.dateFormat
import com.example.m_library.viewmodel.BookViewModel

@Composable
fun BookDetail(
    onBackClicked: () -> Unit,
    bookViewModel: BookViewModel,
    onEditClick: () -> Unit
) {
    val bookState by bookViewModel.bookState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        bookState.book?.let { book: Book ->
            val deleteClicked = remember {
                {
                    bookViewModel.deleteBook(book = book)
                    onBackClicked()
                }
            }

            val editClicked = remember {
                {
                    bookViewModel.editEvent(EditBookEvents.RestoreDetails)
                    onEditClick()
                }
            }

            DetailTopAppBar(
                onBackClicked = onBackClicked,
                onDeleteClick = deleteClicked,
                onEditClick = editClicked
            )

            Column(Modifier.padding(5.dp), horizontalAlignment = CenterHorizontally) {


                ProgressIndicator(
                    readChapters = book.currentChapter,
                    totChapters = book.totalChapters,
                    fontSize = 20.sp,
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(7.dp))


                Text(
                    text = book.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.h6,
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
                    details = book.readStatus.name
                )

                MoreDetail(
                    title = stringResource(id = R.string.finishby),
                    details = dateFormat.format(book.readByDate)
                )
            }

            MoreDetail(
                title = stringResource(id = R.string.currentChp),
                details = book.currentChapter.toString()
            )
            MoreDetail(
                title = stringResource(id = R.string.totChap),
                details = book.totalChapters.toString()
            )
        }
    }
}

@Composable
fun MoreDetail(
    modifier: Modifier = Modifier,
    title: String,
    details: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
                fontSize = 16.sp
            )
            Text(
                text = details,
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp
            )
        }
        Divider()
    }
}
