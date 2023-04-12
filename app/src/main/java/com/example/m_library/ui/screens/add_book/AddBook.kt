package com.example.m_library.ui.screens.add_book

import android.util.Log
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
fun AddBook(
    bookViewModel: BookViewModel,
    onCloseDialog: () -> Unit
) {

    val addBookState by bookViewModel.bookDetailState.collectAsState()
    val chptError = bookViewModel.validChapter(
        addBookState.readChapters.safeToInt(),
        addBookState.totalChapters.safeToInt()
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
                    //x Add book
                    TopView(
                        onCloseDialog = onCloseDialog,
                        iconText = stringResource(id = R.string.add_book)
                    )

                    TextFieldsSection1(
                        title = addBookState.title,
                        onTitleChanged = {
                            bookViewModel.editEvent(
                                event = EditBookEvents.OnTitleChange(
                                    it
                                )
                            )
                        },
                        author = addBookState.author,
                        onAuthorChanged = { bookViewModel.editEvent(EditBookEvents.OnAuthorChange(it)) },
                        titleError = !bookViewModel.validText(addBookState.title),
                        authError = !bookViewModel.validText(addBookState.author)
                    )
                    Log.i("Add Book", "${bookViewModel.validText(addBookState.title)}")

                    TextFieldsSection2(
                        readChpt = addBookState.readChapters,
                        onReadChptsChanged = {
                            bookViewModel.editEvent(
                                EditBookEvents.OnRdChaptsChange(
                                    it
                                )
                            )
                        },
                        totChpts = addBookState.totalChapters,
                        onTotChptChanged = {
                            bookViewModel.editEvent(
                                EditBookEvents.OnTChaptsChange(
                                    it
                                )
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

                    DatePicker(date = addBookState.readByDate)

                    Spacer(modifier = Modifier.height(5.dp))


                    AddEditButton(
                        btnId = R.string.save,
                        onBtnClicked = {
                            bookViewModel.editEvent(EditBookEvents.SaveBook)
                            onCloseDialog()
                        },
                        btnEnabled = bookViewModel.validateInput()
                    )
                }
            }
        }
    }
}

