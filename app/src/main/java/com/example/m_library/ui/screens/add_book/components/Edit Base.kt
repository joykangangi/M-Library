package com.example.m_library.ui.screens.add_book.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.m_library.R
import com.example.m_library.util.safeToInt
import com.example.m_library.viewmodel.BookViewModel


//Full Dialog
@Composable
fun AddEditDialog(
    bookViewModel: BookViewModel,
    onCloseDialog: () -> Unit,
    stringId: Int,
    onTitleChange:(String) -> Unit,
    onAuthorChange: (String) -> Unit,
    onReadChaptsChange: (String) -> Unit,
    onTotChaptsChange: (String) -> Unit,
    btnId: Int
) {

    val titleChange = remember {
        { onTitleChange }
    }

    val authChange = remember {
        { onAuthorChange }
    }

    val readChange = remember {
        { onReadChaptsChange }
    }

    val totChange = remember {
        { onTotChaptsChange }
    }

    val addEditState by bookViewModel.bookDetailState.collectAsState()
    val chptError = bookViewModel.validChapter(
        addEditState.readChapters.safeToInt(),
        addEditState.totalChapters.safeToInt()
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface)
                    .verticalScroll(state = rememberScrollState())
                    .padding(start = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //x Add book
                TopView(
                    onCloseDialog = onCloseDialog,
                    iconTextId = stringId
                )

                EditTextField(
                    title = addEditState.title,
                    onTitleChanged = titleChange(),
                    author = addEditState.author,
                    onAuthorChanged = authChange(),
                    titleError = !bookViewModel.validText(addEditState.title),
                    authError = !bookViewModel.validText(addEditState.author)
                )

                EditChaptersField(
                    readChpt = addEditState.readChapters,
                    onReadChptsChanged = readChange(),
                    totChpts = addEditState.totalChapters,
                    onTotChptChanged = totChange(),
                    chptError = chptError
                )
                if (!chptError)
                    Text(
                        text = stringResource(id = R.string.valid_chp),
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )


                Spacer(modifier = Modifier.height(6.dp))

                DatePicker(date = addEditState.readByDate)

                Spacer(modifier = Modifier.height(5.dp))


                AddEditButton(
                    btnId = btnId,
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
