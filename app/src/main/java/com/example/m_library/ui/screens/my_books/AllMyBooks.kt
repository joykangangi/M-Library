package com.example.m_library.ui.screens.my_books

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AllMyBooks(
    //Viewmodel
    isDeadline: Boolean,
    onDeadLnClicked: () -> Unit
) {
    //state from vm

    val lazyListState = rememberLazyListState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 12.dp, top = 8.dp, end = 12.dp)) {

        BooksTopAppBar(isDeadLine = isDeadline, onDeadLineClicked = onDeadLnClicked, elevation = lazyListState.elevation )
        
        LazyColumn(state = lazyListState) {

        }
    }
}


//Extended Variable, liftOnScroll
val LazyListState.elevation: Dp
    get() = if (firstVisibleItemIndex == 0) {
        minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        AppBarDefaults.TopAppBarElevation
    }