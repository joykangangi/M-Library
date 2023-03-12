package com.example.m_library.ui.screens.add_book

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.R
import com.example.m_library.model.Book
import com.example.m_library.model.ReadingStatus
import com.example.m_library.ui.screens.add_book.components.AddBookEvents
import com.example.m_library.ui.screens.add_book.components.DateSaver
import com.example.m_library.ui.screens.add_book.components.ReadingStatusRadio
import com.example.m_library.util.localDateToDate
import com.example.m_library.viewmodel.BookViewModel
import java.util.*


//Full Dialog
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddBook(
    bookViewModel: BookViewModel,
    onCloseDialog: () -> Unit
) {

    val addBookState = bookViewModel.addBookState.value


    //store the dialog open or closed
    var dialogOpen by remember {
        mutableStateOf(true)
    }

    val choiceList =
        listOf(ReadingStatus.TO_READ.name, ReadingStatus.READING.name, ReadingStatus.COMPLETED.name)

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
                        .padding(start = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    //x Add book
                    Row(
                        modifier = Modifier.padding(start = 3.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onCloseDialog) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = stringResource(
                                    id = R.string.close
                                ),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.add_book),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    OutlinedTextField(
                        value = addBookState.title,
                        onValueChange = {
                            bookViewModel.getAddEvent(event = AddBookEvents.OnTitleChange(it))
                        },
                        maxLines = 2,
                        label = {
                            Text(
                                text = stringResource(id = R.string.titl)
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )

                    OutlinedTextField(
                        value = addBookState.author,
                        onValueChange = {
                            bookViewModel.getAddEvent(AddBookEvents.OnAuthorChange(it))
                        },
                        maxLines = 2,
                        label = {
                            Text(
                                text = stringResource(id = R.string.author)
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(end = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(0.4f),
                            value = addBookState.readChapters,
                            onValueChange = {
                                bookViewModel.getAddEvent(AddBookEvents.OnRdChaptsChange(it))
                            },
                            maxLines = 1,
                            label = {
                                Text(
                                    text = stringResource(id = R.string.currentChp)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.weight(0.4f),
                            value = addBookState.totalChapters,
                            onValueChange = {
                                bookViewModel.getAddEvent(AddBookEvents.OnTChaptsChange(it))
                            },
                            maxLines = 1,
                            label = {
                                Text(
                                    text = stringResource(id = R.string.totChap)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }
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
                            bookViewModel.getAddEvent(AddBookEvents.OnSelectChange(it))
                        }
                    )

                    DateSaver(date = addBookState.readByDate, onDateChanged = {
                        bookViewModel.getAddEvent(AddBookEvents.OnDateChange(it))
                    })
                    Spacer(modifier = Modifier.height(5.dp))

                    Button(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .align(Alignment.CenterHorizontally), onClick = {
                        bookViewModel.addBook(
                            Book(
                                title = addBookState.title,
                                author = addBookState.author,
                                readStatus = ReadingStatus.valueOf(choiceList[addBookState.selectedStatus]),
                                currentChapter = addBookState.readChapters.toInt(),
                                totalChapters = addBookState.totalChapters.toInt(),
                                readByDate = localDateToDate(addBookState.readByDate)
                            )
                        )
                        onCloseDialog()
                        Log.i(
                            "AddBook", " ${
                                Book(
                                    title = addBookState.title,
                                    author = addBookState.author,
                                    readStatus = ReadingStatus.valueOf(choiceList[addBookState.selectedStatus]),
                                    currentChapter = addBookState.readChapters.toInt(),
                                    totalChapters = addBookState.totalChapters.toInt(),
                                    readByDate = localDateToDate(addBookState.readByDate)
                                )
                            }"
                        )
                        /*TODO Validation*/

                    }) {
                        Text(text = stringResource(id = R.string.save), fontSize = 21.sp)
                    }

                }
            }
        }
    }
}


