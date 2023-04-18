package com.example.m_library.app.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun TextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
    ),
    label: String? = null,
    error: String? = null,
) {
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
                        Text(
                            text = label
                        )
                    }
                } else null,
                keyboardOptions = keyboardOptions,
            )
            ErrorText(error)
        }
    )
}