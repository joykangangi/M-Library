package com.example.m_library.books

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.theme.primaryPadding
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    books: ImmutableList<Book>,
    onBookClicked: (Book) -> Unit,
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
            if (books.isEmpty()) {
                EmptyList(modifier = Modifier.fillMaxSize())
            } else LazyColumn(
                modifier = Modifier.padding(contentPadding),
                contentPadding = PaddingValues(primaryPadding()),
                verticalArrangement = Arrangement.spacedBy(primaryPadding()),
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