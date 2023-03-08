package com.example.m_library.ui.screens.book_detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.m_library.R

@Composable
fun DetailTopAppBar(bookTitle: String) {
    TopAppBar(
        title = {
            Text(text = bookTitle)
        },
        navigationIcon = {
            IconButton(onClick = {
                //Todo Nav
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                        id = R.string.go_back
                    )
                )
            }
        }
    )
}