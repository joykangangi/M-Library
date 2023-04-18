package com.example.m_library.bookstats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.app.navigation.NavigationTransitions
import com.ramcosta.composedestinations.annotation.Destination

@Destination("books/stats", style = NavigationTransitions::class)
@Composable
fun BookStatsRoute(
    modifier: Modifier = Modifier,
    viewModel: BookStatsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    BookStatsScreen(
        modifier = modifier,
        state = state,
    )
}