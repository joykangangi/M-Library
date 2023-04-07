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
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditBook(
    bookViewModel: BookViewModel,
    onCloseEdit: () -> Unit
) {

    val detailState by bookViewModel.bookDetailState.collectAsState()
    val chptError = bookViewModel.validChapter(
        detailState.readChapters.safeToInt(),
        detailState.totalChapters.safeToInt()
    )

    //store the dialog open or closed
    var dialogOpen by remember {
        mutableStateOf(true)
    }

    if (dialogOpen) {
        Dialog(
            onDismissRequest = {
                dialogOpen = false
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState())
                        .padding(start = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    //x Edit book
                    TopView(
                        onCloseDialog = onCloseEdit,
                        iconText = stringResource(id = R.string.edit)
                    )

                    TextFieldsSection1(
                        title = detailState.title,
                        onTitleChanged = {
                            bookViewModel.editEvent(event = EditBookEvents.OnTitleChange(it))
                            bookViewModel.editEvent(
                                event = EditBookEvents.EnableEdit
                            )
                        },
                        author = detailState.author,
                        onAuthorChanged = {
                            bookViewModel.editEvent(EditBookEvents.OnAuthorChange(it))
                            bookViewModel.editEvent(
                                event = EditBookEvents.EnableEdit
                            )
                        },
                        titleError = !bookViewModel.validText(detailState.title),
                        authError = !bookViewModel.validText(detailState.author)
                    )

                    TextFieldsSection2(
                        readChpt = detailState.readChapters,
                        onReadChptsChanged = {
                            bookViewModel.editEvent(
                                EditBookEvents.OnRdChaptsChange(
                                    it
                                )
                            )
                            bookViewModel.editEvent(
                                event = EditBookEvents.EnableEdit
                            )
                        },
                        totChpts = detailState.totalChapters,
                        onTotChptChanged = {
                            bookViewModel.editEvent(EditBookEvents.OnTChaptsChange(it))
                            bookViewModel.editEvent(
                                event = EditBookEvents.EnableEdit
                            )
                        },
                        chptError = chptError
                    )
                    if (!chptError)
                        Text(
                            text = stringResource(id = R.string.valid_chp),
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )

                    Spacer(modifier = Modifier.height(6.dp))

                    DateSaver(date = detailState.readByDate, onDateChanged = {
                        bookViewModel.editEvent(EditBookEvents.OnDateChange(it))
                        bookViewModel.editEvent(
                            event = EditBookEvents.EnableEdit
                        )
                    })


                    Spacer(modifier = Modifier.height(5.dp))

                    if (detailState.isEditing) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .align(Alignment.CenterHorizontally),
                            enabled = bookViewModel.validateInput(),
                            onClick = {
                                bookViewModel.editEvent(EditBookEvents.SaveBook)
                                onCloseEdit()
                            }) {
                            Text(text = stringResource(id = R.string.update), fontSize = 21.sp)
                        }
                    }
                }
            }
        }
    }
}

