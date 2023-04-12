package com.example.m_library.ui.screens.add_book.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun DatePicker(
    date: LocalDate
) {

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDate by remember { mutableStateOf(date) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("EEE MMM d,yyyy").format(selectedDate)
        }
    }

    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]


    // DatePickerDialog with a listener function that updates selectedDateText
    val datePicker = remember(context) {
        DatePickerDialog(
            context,
            R.style.DatePickerStyle,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
            },
            year,
            month,
            dayOfMonth  //initial selected date in the dialog.
        )
    }
    datePicker.datePicker.minDate = calendar.timeInMillis


    Row(
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { datePicker.show() }) {
            Text(text = stringResource(id = R.string.finishby))
        }
        Text(text = formattedDate)

    }

}