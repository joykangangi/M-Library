package com.example.m_library.editbook

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.app.navigation.NavigationTransitions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination("books/edit", style = NavigationTransitions::class)
@Composable
fun EditBookRoute(
    bookId: Long?,
    modifier: Modifier = Modifier,
    viewModel: EditBookViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getBook(bookId)
    })

    val state by viewModel.state.collectAsState()

    val onBack: () -> Unit = remember {
        {
            navigator.navigateUp()
        }
    }
    val onSave: () -> Unit = remember {
        {
            viewModel.save()
            onBack()
        }
    }

    EditBookScreen(
        modifier = modifier,
        state = state,
        updateBook = viewModel::updateBook,
        isEdit = bookId != null,
        onBack = onBack,
        onSave = onSave,
    )
}