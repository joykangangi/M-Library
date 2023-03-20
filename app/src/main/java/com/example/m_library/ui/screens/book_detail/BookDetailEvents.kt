package com.example.m_library.ui.screens.book_detail

import java.time.LocalDate


sealed class EditBookEvents {
    data class OnTitleChange(val title: String) : EditBookEvents()
    data class OnAuthorChange(val author: String) : EditBookEvents()
    data class OnTChaptsChange(val totChap: String) : EditBookEvents()
    data class OnRdChaptsChange(val rdChap: String) : EditBookEvents()
    data class OnSelectChange(val selectedIndex: Int) : EditBookEvents()
    data class OnDateChange(val dateChange: LocalDate) : EditBookEvents()

    object SaveBook: EditBookEvents()
}
