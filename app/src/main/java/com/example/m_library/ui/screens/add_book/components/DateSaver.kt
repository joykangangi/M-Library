package com.example.m_library.ui.screens.add_book.components
/*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
    date: LocalDate
) {

    var pickedDate: LocalDate by remember { mutableStateOf(date) }

    val dateDialogState = rememberMaterialDialogState()

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("EEE MMM d, yyyy")
                .format(pickedDate)
        }
    }
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        OutlinedButton(onClick = {
            dateDialogState.show()
        }) {
            Text(text = stringResource(id = R.string.finishby))
        }
        Text(text = formattedDate)
    }

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "OK")
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
*/