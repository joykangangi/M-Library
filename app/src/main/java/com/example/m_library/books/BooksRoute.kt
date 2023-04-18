package com.example.m_library.books

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.navigation.NavigationTransitions
import com.example.m_library.destinations.BookDetailRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination("books", style = NavigationTransitions::class)
@Composable
fun BooksRoute(
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val onBookClicked = remember {
        { book: Book ->
            navigator.navigate(BookDetailRouteDestination(book.id!!))
        }
    }

    BooksScreen(
        modifier = modifier,
        books = viewModel.books.collectAsState().value,
        onBookClicked = onBookClicked,
    )
}