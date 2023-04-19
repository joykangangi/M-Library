package com.example.m_library.ui.screens.book_detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.m_library.R
import com.example.m_library.model.Book

@Composable
fun DetailTopAppBar(onBackClicked: () -> Unit, onDeleteClick:()-> Unit,onEditClick: () -> Unit) {
    val editClick = remember {
        { onEditClick() }
    }

    val backClick = remember {
        { onBackClicked() }
    }

    val deleteClick = remember {
        { onDeleteClick() }
    }


    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.book_det))
        },
        navigationIcon = {
            IconButton(onClick = backClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                        id = R.string.go_back
                    )
                )
            }
        },
        actions = {
            IconButton(onClick = deleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(id = R.string.del))
            }
            IconButton(onClick = editClick) {
                Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = stringResource(id = R.string.edit))
            }
        }
    )
}
