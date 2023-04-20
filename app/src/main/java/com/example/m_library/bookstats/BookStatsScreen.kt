package com.example.m_library.bookstats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.m_library.R
import com.example.m_library.app.theme.mediumPadding
import com.example.m_library.app.theme.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookStatsScreen(
    state: BookStatsState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.stats),
                    )
                },
                actions = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.AutoGraph,
                                contentDescription = null,
                            )
                        }
                    )
                }
            )
        },
        content = { scaffoldPadding ->
            LazyColumn(
                modifier = Modifier.padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(smallPadding()),
                contentPadding = PaddingValues(mediumPadding()),
                content = {
                    item {
                        ExpandableCard(
                            title = "Finished Books",
                            books = state.finished,
                        )
                    }
                    item {
                        ExpandableCard(
                            title = "Currently Reading",
                            books = state.reading,
                        )
                    }
                    item {
                        ExpandableCard(
                            title = "To Read",
                            books = state.future,
                        )
                    }
                }
            )
        }
    )
}