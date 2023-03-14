package com.example.m_library.ui.screens.book_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.R
import com.example.m_library.model.Book
import com.example.m_library.model.Book.ReadingStatus.choiceList
import com.example.m_library.ui.screens.my_books.ProgressIndicator
import com.example.m_library.util.localDateToDate
import com.example.m_library.util.safeToInt
import com.example.m_library.viewmodel.BookViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookDetail(
    onBackClicked: () -> Unit,
    bookViewModel: BookViewModel,
    onEditClick: () -> Unit,
    id: Long
) {
    // Format date as: Thu Jan 3, 2023
    val dateFormat = SimpleDateFormat("EEE MMM d, yyyy", Locale.ENGLISH)
    val bookState = bookViewModel.bookDetailState.value


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        DetailTopAppBar(
            onBackClicked = onBackClicked,
            onDeleteClick = {
                Log.i("DETAIL VIEW","$bookState")

                     bookViewModel.setSelectedBook(book = Book(
                         id = id,
                         author = bookState.author,
                         title = bookState.title,
                         currentChapter = bookState.readChapters.toInt(),
                         totalChapters = bookState.totalChapters.toInt(),
                         readStatus = bookState.selectedStatus,
                         readByDate = localDateToDate(bookState.readByDate)
                     ))
                     bookViewModel.deleteBook(book = Book(
                         id=id,
                         author = bookState.author,
                         title = bookState.title,
                         currentChapter = bookState.readChapters.toInt(),
                         totalChapters = bookState.totalChapters.toInt(),
                         readStatus = bookState.selectedStatus,
                         readByDate = localDateToDate(bookState.readByDate)
                     )
                     )

                onBackClicked()
            },
            onEditClick = { onEditClick() }
        )

        Spacer(modifier = Modifier.height(5.dp))
        Column(Modifier.padding(3.dp), horizontalAlignment = CenterHorizontally) {


            ProgressIndicator(
                readChapters = bookState.readChapters.safeToInt(),
                totChapters = bookState.totalChapters.safeToInt(),
                fontSize = 20.sp,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(7.dp))


            Text(
                text = bookState.title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = bookState.author,
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
                details = choiceList[bookState.selectedStatus]
            )

                MoreDetail(
                    title = stringResource(id = R.string.finishby),
                    details = dateFormat.format(localDateToDate(bookState.readByDate))
                )
            }

            MoreDetail(
                title = stringResource(id = R.string.currentChp),
                details = bookState.readChapters
            )
            MoreDetail(
                title = stringResource(id = R.string.totChap),
                details = bookState.totalChapters
            )
        }
    }


@Composable
fun MoreDetail(
    title: String,
    details: Any
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
                fontSize = 16.sp
            )
            Text(
                text = details.toString(),
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp
            )
        }
        Divider()
    }
}
