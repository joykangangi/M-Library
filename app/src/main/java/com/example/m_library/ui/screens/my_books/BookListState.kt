package com.example.m_library.ui.screens.my_books

import androidx.compose.runtime.Stable
import com.example.m_library.model.Book

@Stable
data class BookState(
    val book: Book? = null
)