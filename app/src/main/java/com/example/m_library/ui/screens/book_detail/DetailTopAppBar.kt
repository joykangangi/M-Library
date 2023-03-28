package com.example.m_library.ui.screens.book_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.m_library.R

@Composable
fun DetailTopAppBar(onBackClicked: () -> Unit, onDeleteClick:()-> Unit,onEditClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.book_det))
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                        id = R.string.go_back
                    )
                )
            }
        },
        actions = {
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(id = R.string.del))
            }
            IconButton(onClick = onEditClick) {
                Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = stringResource(id = R.string.edit))
            }
        }
    )
}
