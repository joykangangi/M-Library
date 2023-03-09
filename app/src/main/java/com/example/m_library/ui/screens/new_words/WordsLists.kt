package com.example.m_library.ui.screens.new_words


import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m_library.model.NewWord

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordsList(/*Todo VM*/) {

    val words = listOf("s", "h", "a", "r")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, top = 8.dp, end = 12.dp)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(5.dp)
        ) {
            items(words) {
                WordItem(newWord = NewWord(newWord = "", meaning = ""),
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
                )
            }
        }


        Box {
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { /*TODO Nav*/ }) {

            }
        }
    }
}