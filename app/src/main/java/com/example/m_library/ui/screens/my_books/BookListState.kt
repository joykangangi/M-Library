package com.example.m_library.ui.screens.my_books

import com.example.m_library.model.Book

data class BookListState(
    val isDeadLine: Boolean = false,
    val bookList: List<Book> = listOf()
)

sealed class BookListEvents {
    data class DeadLineChanged(val deadLine: Boolean): BookListEvents()
}