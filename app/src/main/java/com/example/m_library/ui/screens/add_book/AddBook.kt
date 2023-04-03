package com.example.m_library.ui.screens.add_book

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
import com.example.m_library.model.Book.ReadingStatus.choiceList
import com.example.m_library.ui.screens.add_book.components.*
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
                    TopView(onCloseDialog = onCloseDialog)

                    TextFieldsSection1(
                        title = addBookState.title,
                        onTitleChanged = { bookViewModel.editEvent(event = EditBookEvents.OnTitleChange(it)) },
                        author = addBookState.author,
                        onAuthorChanged = { bookViewModel.editEvent(EditBookEvents.OnAuthorChange(it)) },
                        titleError = bookViewModel.validText(addBookState.title),
                        authError = bookViewModel.validText(addBookState.author)
                    )

                    TextFieldsSection2(
                        readChpt = addBookState.readChapters,
                        onReadChptsChanged = { bookViewModel.editEvent(EditBookEvents.OnRdChaptsChange(it)) },
                        totChpts = addBookState.totalChapters,
                        onTotChptChanged = { bookViewModel.editEvent(EditBookEvents.OnTChaptsChange(it)) },
                        chptError = bookViewModel.validChapter(addBookState.readChapters.toInt(), addBookState.totalChapters.toInt())
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = stringResource(id = R.string.reading_st),
                        style = MaterialTheme.typography.h6
                    )
                    Divider(Modifier.padding(top = 3.dp))


                    ReadingStatusRadio(
                        options = choiceList,
                        selectedIndex = addBookState.selectedStatus,
                        onSelected = {
                            bookViewModel.editEvent(EditBookEvents.OnSelectChange(it))
                        }
                    )

                    DateSaver(date = addBookState.readByDate, onDateChanged = {
                        bookViewModel.editEvent(EditBookEvents.OnDateChange(it))
                    })

                    Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .align(Alignment.CenterHorizontally),
                        enabled = addBookState.isValid,
                        onClick = {
                            bookViewModel.validateInput()
                            bookViewModel.editEvent(EditBookEvents.SaveBook)
                            onCloseDialog()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.save), fontSize = 21.sp)
                    }
                }
            }
        }
    }
}

