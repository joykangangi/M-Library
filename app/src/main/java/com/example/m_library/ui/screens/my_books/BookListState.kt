package com.example.m_library.ui.screens.my_books

import com.example.m_library.model.Book
import kotlinx.coroutines.flow.Flow


data class BookState(
    val book: Book? = null
)

sealed class BookListEvents {
    data class DeadLineChanged(val deadLine: Boolean): BookListEvents()
}