package com.example.m_library.ui.screens.add_book.components

import java.time.LocalDate


sealed class EditBookEvents {
    data class OnTitleChange(val title: String) : EditBookEvents()
    data class OnAuthorChange(val author: String) : EditBookEvents()
    data class OnTChaptsChange(val totChap: String) : EditBookEvents()
    data class OnRdChaptsChange(val rdChap: String) : EditBookEvents()
    data class OnDateChange(val dateChange: LocalDate) : EditBookEvents()
    object EnableEdit: EditBookEvents()
    object SaveBook: EditBookEvents()
    object RestoreDetails: EditBookEvents() //restore details when moving from Detail to Edit Screen

}