package com.example.m_library.ui.screens.my_books

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.m_library.model.Book
import com.example.m_library.model.ReadingStatus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllMyBooks(
    //Viewmodel
    isDeadline: Boolean ,
    onDeadLnClicked: () -> Unit,
    modifier: Modifier
) {
    //state from vm

    val lazyListState = rememberLazyListState()
    val l = listOf(1,2,3)
    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 12.dp, top = 8.dp, end = 12.dp)) {

        BooksTopAppBar(isDeadLine = isDeadline, onDeadLineClicked = onDeadLnClicked, elevation = lazyListState.elevation )
        
        LazyColumn(state = lazyListState, modifier = modifier.animateContentSize()) {
           items(items = l, key = null ) {
               BookItem(onBookClicked = { /*TODO Nav*/ },
                   book = Book(title = "", author = "", readStatus = ReadingStatus.READING, currentChapter = 4, totalChapters = 10),
               modifier = modifier.animateItemPlacement(
                   animationSpec = tween(
                       durationMillis = 500,
                       easing = LinearOutSlowInEasing
                   )
               ))
            }
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