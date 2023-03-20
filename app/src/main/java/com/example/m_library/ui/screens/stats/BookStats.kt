package com.example.m_library.ui.screens.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.m_library.R
import com.example.m_library.ui.screens.book_detail.MoreDetail
import com.example.m_library.viewmodel.BookViewModel

@Composable
fun BookStats(
    viewModel: BookViewModel = hiltViewModel()
) {

    val totalBooks = viewModel.allList.collectAsState(initial = listOf()).value.size
    val finishedBooks = viewModel.finishedList.collectAsState(initial = listOf()).value.size
    val readingBooks = viewModel.readingList.collectAsState(initial = listOf()).value.size

    Surface(
        modifier = Modifier
            .padding(start = 18.dp, top = 10.dp, end = 8.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(16)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.stats),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier.size(55.dp),
                    painter = painterResource(id = R.drawable.ic_graph),
                    contentDescription = null)
            }

            MoreDetail(title = "Total Books", details = totalBooks, spaced = 10.dp)
            MoreDetail(title = "Finished Books", details = finishedBooks, spaced = 10.dp)
            MoreDetail(title = "Currently Reading", details =readingBooks, spaced = 10.dp)
            MoreDetail(title = "To Read", details = (totalBooks -(readingBooks+ finishedBooks)), spaced = 10.dp)

        }


    }
}