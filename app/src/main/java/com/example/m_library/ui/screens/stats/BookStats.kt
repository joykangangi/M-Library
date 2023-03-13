package com.example.m_library.ui.screens.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.ui.screens.book_detail.MoreDetail
import com.example.m_library.viewmodel.BookViewModel

@Composable
fun BookStats(
    viewModel: BookViewModel
) {
    val totalBooks = viewModel.bookListFlow.collectAsState(initial = listOf()).value.size
    val finishedBooks = viewModel.finishedListFlow.collectAsState(initial = listOf()).value.size
    val readingBooks = viewModel.readingListFlow.collectAsState(initial = listOf()).value.size

    Surface(
        modifier = Modifier
            .padding(start = 3.dp, top = 10.dp, end = 3.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.stats),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )

            MoreDetail(title = "Total Books", details = totalBooks)
            MoreDetail(title = "Finished Books", details = finishedBooks)
            MoreDetail(title = "Currently Reading", details =readingBooks)

        }


    }
}