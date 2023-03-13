package com.example.m_library.ui.screens.my_books

import android.util.Log
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.model.Book
import com.example.m_library.viewmodel.BookViewModel
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllMyBooks(
    modifier: Modifier = Modifier,
    bookViewModel: BookViewModel,
    onClickBook: (Book) -> Unit
) {
    val bookList = bookViewModel.bookListFlow.collectAsState(initial = listOf())


    Column(
        modifier = modifier
            .size(596.dp)
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            }
        )

        LazyColumn(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        ) {
            items(items = bookList.value) {book:Book ->
                BookItem(
                    book = book,
                    onBookClicked = {
                        bookViewModel.setSelectedBook(book)
                        onClickBook(book)
                    },
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )
            }
        }
    }
}