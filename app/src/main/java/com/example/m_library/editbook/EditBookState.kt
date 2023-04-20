package com.example.m_library.editbook

import com.example.m_library.app.data.local.Book

data class EditBookState(
    val book: Book = Book(),
    val titleError: Int? = null,
    val authorError: Int? = null,
    val chaptersError: Int? = null,
    val buttonEnabled: Boolean = false,
)
