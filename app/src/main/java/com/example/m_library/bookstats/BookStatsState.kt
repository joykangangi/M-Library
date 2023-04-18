package com.example.m_library.bookstats

import com.example.m_library.app.data.local.Book
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class BookStatsState(
    val finished: ImmutableList<Book> = persistentListOf(),
    val reading: ImmutableList<Book> = persistentListOf(),
    val future: ImmutableList<Book> = persistentListOf(),
)
