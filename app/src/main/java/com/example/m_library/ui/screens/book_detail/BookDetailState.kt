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

data class BookState(
    val book: Book?=null
)


sealed class EditBookEvents {
    data class OnTitleChange(val title: String) : EditBookEvents()
    data class OnAuthorChange(val author: String) : EditBookEvents()
    data class OnTChaptsChange(val totChap: String) : EditBookEvents()
    data class OnRdChaptsChange(val rdChap: String) : EditBookEvents()

    data class OnSelectChange(val selectedIndex: Int) : EditBookEvents()

    data class OnDateChange(val dateChange: LocalDate) : EditBookEvents()

    object SaveBook: EditBookEvents()
    object DeleteBook: EditBookEvents()

}
