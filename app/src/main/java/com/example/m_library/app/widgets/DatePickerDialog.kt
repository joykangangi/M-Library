package com.example.m_library.app.widgets

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.m_library.R
import com.example.m_library.app.theme.smallPadding
import com.example.m_library.app.util.formatted
import java.time.LocalDate
import java.util.Calendar


@Composable
fun DatePicker(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current

    var selectedDate by remember { mutableStateOf(date) }

    val datePicker = remember(context) {
        val calendar = Calendar.getInstance()
        val dialog =  DatePickerDialog(
            context,
            R.style.DatePickerStyle,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]  //initial selected date in the dialog.
        )
        dialog.datePicker.minDate = calendar.timeInMillis
        dialog
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(smallPadding(), Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Button(
                onClick = datePicker::show,
                content = {
                    Text(text = stringResource(id = R.string.finishby))
                }
            )
            Text(text = selectedDate.formatted)
        }
    )

}