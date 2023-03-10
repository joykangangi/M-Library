package com.example.m_library.ui.screens.add_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSaver(
    date: LocalDate,
    onDateChanged: (date: LocalDate) -> Unit
) {
    var pickedDate by remember {
        mutableStateOf(date)
    }
    val dateDialogState = rememberMaterialDialogState()

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE MMM d, yyyy")
                .format(pickedDate)
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = stringResource(id = R.string.finishby))
        }
        Text(text = formattedDate)
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "OK", onClick = { onDateChanged(pickedDate) })
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = stringResource(id = R.string.finishby)
        ) {
            pickedDate = it
        }
    }
}
