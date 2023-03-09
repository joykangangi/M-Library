package com.example.m_library.ui.screens.add_book.components

import java.time.LocalDate

data class AddNoteState(
    val selectedStatus: String? = null,
    val readByDate: LocalDate? = null,
    val title: String = "",
    val author: String = "",
    val totalChapters: Int = 0,
    val readChapters: Int = 0
)