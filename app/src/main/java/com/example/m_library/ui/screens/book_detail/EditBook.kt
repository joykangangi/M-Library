package com.example.m_library.ui.screens.book_detail

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
import com.example.m_library.R
import com.example.m_library.model.Book.ReadingStatus.choiceList
import com.example.m_library.ui.screens.add_book.components.DateSaver
import com.example.m_library.ui.screens.add_book.components.ReadingStatusRadio
import com.example.m_library.viewmodel.AddEditViewModel
import com.example.m_library.viewmodel.BookViewModel
import java.util.*


//Full Dialog
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditBook(
    addEditViewModel: AddEditViewModel,
    onCloseEdit: () -> Unit
) {

    val detailState by addEditViewModel.bookDetailState.collectAsState()


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
                        .padding(start = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    //x Edit book
                    Row(
                        modifier = Modifier.padding(start = 3.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onCloseEdit) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = stringResource(
                                    id = R.string.close
                                ),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.edit),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                    OutlinedTextField(
                        value = detailState.title,
                        onValueChange = {
                            addEditViewModel.editEvent(EditBookEvents.OnTitleChange(it))
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
                        value = detailState.author,
                        onValueChange = {
                            addEditViewModel.editEvent(EditBookEvents.OnAuthorChange(it))
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        OutlinedTextField(
                            modifier = Modifier.weight(0.4f),
                            value = detailState.readChapters,
                            onValueChange = {
                                addEditViewModel.editEvent(EditBookEvents.OnRdChaptsChange(it))
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
                            value = detailState.totalChapters,
                            onValueChange = {
                                addEditViewModel.editEvent(EditBookEvents.OnTChaptsChange(it))
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
                        selectedIndex = detailState.selectedStatus,
                        onSelected = {
                            addEditViewModel.editEvent(EditBookEvents.OnSelectChange(it))
                        }
                    )

                    DateSaver(date = detailState.readByDate, onDateChanged = {
                        addEditViewModel.editEvent(EditBookEvents.OnDateChange(it))
                    })


                    Spacer(modifier = Modifier.height(5.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .align(Alignment.CenterHorizontally),
                        enabled = addEditViewModel.validInput(),
                        onClick = {
                            addEditViewModel.editEvent(EditBookEvents.SaveBook)
                            onCloseEdit()
                        }) {
                        Text(text = stringResource(id = R.string.update), fontSize = 21.sp)
                    }
                }
            }
        }
    }
}

