package com.example.m_library.editbook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.theme.MLibraryTheme
import com.example.m_library.app.theme.mediumPadding
import com.example.m_library.app.theme.smallPadding
import com.example.m_library.app.util.safeToInt
import com.example.m_library.app.widgets.DatePicker
import com.example.m_library.app.widgets.ErrorText
import com.example.m_library.app.widgets.TextField

@Composable
fun EditBookScreen(
    state: EditBookState,
    updateBook: (Book) -> Unit,
    isEdit: Boolean,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onSave: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            EditBookTopBar(
                title = stringResource(id = if (isEdit) R.string.edit else R.string.add_book),
                onBack = onBack,
            )
        },
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(mediumPadding())
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(mediumPadding()),
                content = {
                    TextField(
                        value = state.book.title,
                        onValueChanged = {
                            updateBook(state.book.copy(title = it))
                        },
                        label = stringResource(id = R.string.titl),
                        error = state.titleError?.let { stringResource(id = it) },
                    )
                    TextField(
                        value = state.book.author,
                        onValueChanged = {
                            updateBook(state.book.copy(author = it))
                        },
                        label = stringResource(id = R.string.author),
                        error = state.authorError?.let { stringResource(id = it) },
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            TextField(
                                modifier = Modifier.weight(1f),
                                value = state.book.currentChapter.toString(),
                                onValueChanged = {
                                    updateBook(
                                        state.book.copy(
                                            currentChapter = it.toIntOrNull() ?: "".safeToInt()
                                        )
                                    )
                                },
                                label = stringResource(id = R.string.currentChp),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
                                ),
                            )
                            Spacer(modifier = Modifier.width(smallPadding()))
                            TextField(
                                modifier = Modifier.weight(1f),
                                value = state.book.totalChapters.toString(),
                                onValueChanged = {
                                    updateBook(
                                        state.book.copy(
                                            totalChapters = it.toIntOrNull() ?: "".safeToInt()
                                        )
                                    )
                                },
                                label = stringResource(id = R.string.totChap),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done.also { onSave() },
                                    keyboardType = KeyboardType.Number
                                ),
                            )
                        },
                    )
                    state.chaptersError?.let {
                        ErrorText(text = stringResource(it))
                    }

                    DatePicker(
                        modifier = Modifier.padding(vertical = smallPadding()),
                        date = state.book.readByDate,
                    )

                    Button(modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .align(Alignment.CenterHorizontally),
                        enabled = state.buttonEnabled,
                        onClick = onSave,
                        content = {
                            Text(
                                text = stringResource(id = if (isEdit) R.string.update else R.string.save)
                            )
                        })
                })
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun EditBookScreenPreview() = MLibraryTheme {
    EditBookScreen(
        state = EditBookState(),
        updateBook = {},
        isEdit = true,
        onBack = { },
        onSave = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun AddBookScreenPreview() = MLibraryTheme {
    EditBookScreen(
        state = EditBookState(),
        updateBook = {},
        isEdit = false,
        onBack = { },
        onSave = {},
    )
}