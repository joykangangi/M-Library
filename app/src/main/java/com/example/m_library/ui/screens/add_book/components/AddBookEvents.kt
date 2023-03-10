package com.example.m_library.ui.screens.add_book.components

import java.time.LocalDate

sealed class AddBookEvents{
    data class OnTitleChange(val title: String): AddBookEvents()
    data class OnAuthorChange(val author: String): AddBookEvents()
    data class OnTChaptsChange(val totChap: String): AddBookEvents()
    data class OnRdChaptsChange(val rdChap: String): AddBookEvents()

    data class OnSelectChange(val selectedIndex: Int): AddBookEvents()

    data class OnDateChange(val dateChange: LocalDate): AddBookEvents()
}

