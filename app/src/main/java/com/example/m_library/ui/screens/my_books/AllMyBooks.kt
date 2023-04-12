package com.example.m_library.ui.screens.my_books

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        },
        content = { contentPadding ->
            val books by bookViewModel.allList.collectAsState(initial = listOf())
            val onBookClicked = remember {
                { book: Book ->
                    bookViewModel.setSelectedBook(book = book)
                    onClickBook(book)
                }
            }
            if (books.isEmpty()) {
                EmptyList(modifier = Modifier.fillMaxSize())
            } else LazyColumn(
                modifier = Modifier.padding(contentPadding),
                contentPadding = PaddingValues(
                    12.dp, 12.dp, 12.dp, 85.dp,
                ),
                content = {
                    items(items = books) { book: Book ->
                        BookItem(
                            book = book,
                            onBookClicked = onBookClicked,
                            modifier = Modifier.animateItemPlacement(
                                animationSpec = tween(
                                    durationMillis = 500,
                                    easing = LinearOutSlowInEasing
                                )
                            )
                        )
                    }
                }
            )
        }
    )
}