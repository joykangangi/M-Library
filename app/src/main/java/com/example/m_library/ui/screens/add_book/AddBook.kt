package com.example.m_library.ui.screens.add_book

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.m_library.R
import com.example.m_library.model.ReadingStatus
import com.example.m_library.ui.screens.add_book.components.ReadingStatusRadio
import java.time.LocalDate


//Full Dialog
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddBook() {

    //store the dialog open or closed
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    val choiceList = listOf(ReadingStatus.TO_READ.name, ReadingStatus.READING.name, ReadingStatus.COMPLETED.name)

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
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                }

                //x Add book
                Row(
                    modifier = Modifier.padding(3.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    IconButton(onClick = { /*TODO Navigation back to home screen */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = stringResource(
                                id = R.string.close
                            )
                        )
                    }
                    Text(text = stringResource(id = R.string.add_book))
                }


                OutlinedTextField(
                    //searchQuery = state.searchQuery) { query ->
                    //            viewModel.getEvent(CountryListEvents.OnSearchQueryChange(query))
                    //        }
                    value = "", //from viewmodel
                    onValueChange = {/*TODO VM*/ },
                    maxLines = 2,
                    label = {
                        Text(
                            text = stringResource(id = R.string.titl)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {/*TODO VM*/ },
                    maxLines = 2,
                    label = {
                        Text(
                            text = stringResource(id = R.string.author)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {/*TODO VM*/ },
                    maxLines = 2,
                    label = {
                        Text(
                            text = stringResource(id = R.string.totChap)
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Divider()

                /*
                 selectedIndex = formData.value.selectedStatus?.let { option ->
            // Return the index of the currently selected option
            options.indexOf(option).takeIf { it >= 0 }
                 */

                /*
                onSelected = { index ->
            // Update the form data with the newly selected option
            formData.value = formData.value.copy(selectedStatus = options[index])
        }

                 */

                ReadingStatusRadio(options = choiceList , selectedIndex = 0 /*TODO VM*/ , onSelected = {
                    (it) //Todo VM
                } )

               // DateSaver(date = LocalDate.now()/*Todo Vm.state*/)
            }
        }
    }
}

