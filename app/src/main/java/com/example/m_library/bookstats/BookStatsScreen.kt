package com.example.m_library.bookstats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
    val appBarColor = MaterialTheme.colorScheme.primary
    val barContentColor = MaterialTheme.colorScheme.onPrimary
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = appBarColor,
                    titleContentColor = barContentColor
                ),
                title = {
                    Text(text = stringResource(id = R.string.stats))
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.AutoGraph,
                        tint = barContentColor,
                        contentDescription = null,
                        modifier = modifier.padding(end = smallPadding())
                    )
                }
            )
        },
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(mediumPadding())
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(smallPadding()),
                content = {
                    ExpandableCard(
                        title = "Finished Books",
                        books = state.finished,
                    )

                    ExpandableCard(
                        title = "Currently Reading",
                        books = state.reading,
                    )

                    ExpandableCard(
                        title = "To Read",
                        books = state.future,
                    )
                }
            )
        }
    )
}