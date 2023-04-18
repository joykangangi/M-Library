package com.example.m_library.bookdetail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.m_library.R
import com.example.m_library.app.theme.MLibraryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.book_det))
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onDelete,
                content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.del)
                    )
                }
            )
            IconButton(
                onClick = onEdit, content = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = stringResource(id = R.string.edit)
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun BookDetailAppBarPreview() = MLibraryTheme {
    BookDetailAppBar(onBack = { /*TODO*/ }, onDelete = { /*TODO*/ }) {}
}