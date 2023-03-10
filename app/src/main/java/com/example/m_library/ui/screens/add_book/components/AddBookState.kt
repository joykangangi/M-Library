package com.example.m_library.ui.screens.add_book.components

import java.time.LocalDate


data class AddBookState(
    val selectedStatus: Int = 0,
    val readByDate: LocalDate = LocalDate.now(),
    val title: String = "",
    val author: String = "",
    val totalChapters: String = "",
    val readChapters: String = ""
)