package com.example.m_library.ui.screens.book_detail

import com.example.m_library.model.Book
import java.time.LocalDate

data class BookDetailState(
    val selectedStatus: Int = 0,
    val readByDate: LocalDate = LocalDate.now(),
    val title: String = "",
    val author: String = "",
    val totalChapters: String = "",
    val readChapters: String = ""
)