package com.example.m_library.app.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String? = null,
    error: String? = null,
) {
    //To hide keyboard
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
        content = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChanged,
                maxLines = 1,
                isError = error != null,
                label = if (label != null) {
                    {
                        Text(text = label)
                    }
                } else null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = imeAction,
                    keyboardType = keyboardType
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                            keyboardController?.hide()
                    })
            )
            ErrorText(error)
        }
    )
}