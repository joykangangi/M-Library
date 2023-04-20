package com.example.m_library.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.app.data.local.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    repository: BookRepository
) : ViewModel() {
    val books = repository.getAllBooks().map {
        it.toPersistentList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        persistentListOf()
    )
}