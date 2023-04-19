package com.example.m_library.ui.screens.add_book

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.R
import com.example.m_library.ui.screens.add_book.components.*
import com.example.m_library.util.safeToInt
import com.example.m_library.viewmodel.BookViewModel
import java.util.*




//Full Dialog
@Composable
fun AddBook(
    bookViewModel: BookViewModel = hiltViewModel(),
    onCloseDialog: () -> Unit
) {

    AddEditDialog(
        bookViewModel = bookViewModel,
        onCloseDialog = onCloseDialog,
        stringId = R.string.add_book,
        onTitleChange = { bookViewModel.editEvent(EditBookEvents.OnTitleChange(it)) },
        onAuthorChange = { bookViewModel.editEvent(EditBookEvents.OnAuthorChange(it)) },
        onReadChaptsChange = { bookViewModel.editEvent(EditBookEvents.OnRdChaptsChange(it)) },
        onTotChaptsChange = { bookViewModel.editEvent(EditBookEvents.OnTChaptsChange(it)) },
        btnId = R.string.save
    )

}