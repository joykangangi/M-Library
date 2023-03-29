package com.example.m_library.ui.screens.add_book.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.R

@Composable
fun TopView(onCloseDialog:()->Unit) {
    Row(
        modifier = Modifier.padding(start = 3.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onCloseDialog) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = stringResource(
                    id = R.string.close
                ),
                modifier = Modifier.size(40.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.add_book),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TextFieldsSection1(
    title: String,
    onTitleChanged:(String)->Unit,
    author: String,
    onAuthorChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = title ,
        onValueChange = onTitleChanged,
        maxLines = 2,
        label = {
            Text(
                text = stringResource(id = R.string.titl)
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
    )



    OutlinedTextField(
        value = author,
        onValueChange = onAuthorChanged,
        maxLines = 2,
        label = {
            Text(
                text = stringResource(id = R.string.author)
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
    )
}


@Composable
fun TextFieldsSection2(
    readChpt: String,
    onReadChptsChanged: (String) -> Unit,
    totChpts: String,
    onTotChptChanged: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            modifier = Modifier.weight(0.4f),
            value = readChpt,
            onValueChange = onReadChptsChanged,
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
            value = totChpts ,
            onValueChange = onTotChptChanged,
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
}