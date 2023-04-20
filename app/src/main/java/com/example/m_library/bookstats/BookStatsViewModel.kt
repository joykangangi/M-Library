package com.example.m_library.bookstats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.app.data.local.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookStatsViewModel @Inject constructor(
    repository: BookRepository
) : ViewModel() {
    val state = combine(
        flow = repository.finishedBooks(),
        flow2 = repository.readingBooks(),
        flow3 = repository.futureReads(),
        transform = { finished, reading, future ->
            BookStatsState(
                finished = finished.toPersistentList(),
                reading = reading.toPersistentList(),
                future = future.toImmutableList(),
            )
        }
    ).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        BookStatsState(),
    )
}