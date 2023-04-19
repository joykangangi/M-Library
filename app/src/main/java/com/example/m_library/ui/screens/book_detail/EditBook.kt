package com.example.m_library.ui.screens.book_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.m_library.R
import com.example.m_library.ui.screens.add_book.components.*
import com.example.m_library.util.safeToInt
import com.example.m_library.viewmodel.BookViewModel
import java.util.*


//Full Dialog

@Composable
fun EditBook(
    bookViewModel: BookViewModel,
    onCloseEdit: () -> Unit
) {
    AddEditDialog(
        bookViewModel = bookViewModel,
        onCloseDialog = onCloseEdit,
        stringId = R.string.edit,
        onTitleChange = {
            bookViewModel.editEvent(EditBookEvents.OnTitleChange(it))
            bookViewModel.editEvent(event = EditBookEvents.EnableEdit)
        },
        onAuthorChange = {
            bookViewModel.editEvent(EditBookEvents.OnAuthorChange(it))
            bookViewModel.editEvent(event = EditBookEvents.EnableEdit)
        },
        onReadChaptsChange = {
            bookViewModel.editEvent(EditBookEvents.OnRdChaptsChange(it))
            bookViewModel.editEvent(event = EditBookEvents.EnableEdit)
        },
        onTotChaptsChange = {
            bookViewModel.editEvent(EditBookEvents.OnTChaptsChange(it))
            bookViewModel.editEvent(event = EditBookEvents.EnableEdit)
        },
        btnId = R.string.update
    )

}