package com.example.m_library.bookdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.app.navigation.NavigationTransitions
import com.example.m_library.destinations.EditBookRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination("books/details", style = NavigationTransitions::class)
@Composable
fun BookDetailRoute(
    bookId: Long,
    modifier: Modifier = Modifier,
    viewModel: BookDetailViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getBook(bookId)
    })

    val book by viewModel.book.collectAsState()

    val onBack: () -> Unit = remember {
        {
            navigator.navigateUp()
        }
    }
    val onEdit: () -> Unit = remember {
        {
            navigator.navigate(EditBookRouteDestination(book.id))
        }
    }
    val onDelete: () -> Unit = remember {
        {
            viewModel.deleteBook(book)
            onBack()
        }
    }

    BookDetailScreen(
        modifier = modifier,
        book = book,
        onBack = onBack,
        onEdit = onEdit,
        onDelete = onDelete
    )
}