package com.example.m_library.ui.screens.stats

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.R
import com.example.m_library.model.ExpandableCardModel
import com.example.m_library.viewmodel.BookViewModel

//this instance of vm is independent of state,
@Composable
fun BookStats(
    viewModel: BookViewModel = hiltViewModel()
) {

    // val totalBooks = viewModel.allList.collectAsState(initial = listOf())
    val finishedBooks = viewModel.finishedList.collectAsState(initial = listOf())
    val readingBooks = viewModel.readingList.collectAsState(initial = listOf())
    val futureReads = viewModel.futureReads.collectAsState(initial = listOf())

    // val totalBookSize = totalBooks.value.size
    val finishedBookSize = finishedBooks.value.size
    val readingBookSize = readingBooks.value.size
    val futureReadSize = futureReads.value.size


    val expandedState = viewModel.expandedState.collectAsState().value
    Log.i("Book Stats- Expanded State", "$expandedState")

    Column(
        modifier = Modifier
            .height(594.dp)
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TopAppBar(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.stats),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier.size(55.dp),
                    painter = painterResource(id = R.drawable.ic_graph),
                    contentDescription = null
                )
            }
        }

        ExpandableCard(
            card = ExpandableCardModel(
                title = "Finished Books",
                number = finishedBookSize.toString(),
                body = finishedBooks.value
            ),
            expanded = expandedState.isFinishExpanded,
            onCardArrowClick = {
                viewModel.onCardArrowClicked(expandedEvents = ExpandedEvents.ExpandFinish)
            }
        )

        ExpandableCard(
            card = ExpandableCardModel(
                title = "Currently Reading",
                number = readingBookSize.toString(),
                body = readingBooks.value
            ),
            expanded = expandedState.isReadingExpanded,
            onCardArrowClick = {
                viewModel.onCardArrowClicked(expandedEvents = ExpandedEvents.ExpandReading)
            }
        )

        ExpandableCard(
            card = ExpandableCardModel(
                title = "To Read",
                number = futureReadSize.toString(),
                body = futureReads.value
            ),
            expanded = expandedState.isToReadExpanded,
            onCardArrowClick = {
                viewModel.onCardArrowClicked(expandedEvents = ExpandedEvents.ExpandToRead)
            }
        )
    }
}