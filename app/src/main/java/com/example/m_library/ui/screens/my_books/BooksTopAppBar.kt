package com.example.m_library.ui.screens.my_books

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.example.m_library.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BooksTopAppBar(
    isDeadLine: Boolean,
    onDeadLineClicked: () -> Unit,
    elevation: Dp
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        elevation = elevation,
        actions = {
            Chip(onClick = onDeadLineClicked, shape = CircleShape) {
                if (isDeadLine){
                    Image(imageVector = Icons.Outlined.CheckCircle, contentDescription = stringResource(id = R.string.deadline))
                }
                Text(text = stringResource(id = R.string.deadLn))

            }
        }
    )
}