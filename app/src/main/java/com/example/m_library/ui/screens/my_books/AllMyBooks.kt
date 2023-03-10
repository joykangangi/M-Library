package com.example.m_library.ui.screens.my_books

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.R
import com.example.m_library.model.Book
import com.example.m_library.model.ReadingStatus
import java.util.*

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun AllMyBooks(
    //Viewmodel
    isDeadline: Boolean ,
    onDeadLnClicked: () -> Unit,
    modifier: Modifier
) {
    //state from vm

    val lazyListState = rememberLazyListState()

    Column(modifier = modifier
        .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            elevation = lazyListState.elevation
        )
        Chip(modifier = modifier.padding(start = 9.dp, bottom = 3.dp), onClick = onDeadLnClicked, shape = CircleShape) {
            if (isDeadline){
                Image(imageVector = Icons.Filled.CheckCircle, contentDescription = stringResource(id = R.string.deadline))
            }
            Text(text = stringResource(id = R.string.deadLn), fontSize = 21.sp)

        }
        Divider()
        LazyColumn(
            modifier = Modifier.padding(start = 12.dp, bottom = 4.dp, end = 12.dp),
            state = lazyListState
        ) {
           items(items = listOf("a","b"), key = null) {
               BookItem(onBookClicked = { /*TODO Nav*/ },
                   book = Book(
                       title = "Mary", author = "Joy", readStatus = ReadingStatus.READING, currentChapter = 4, totalChapters = 10),
               modifier = Modifier.animateItemPlacement(
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