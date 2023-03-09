package com.example.m_library.ui.screens.add_book.components

sealed class AddNoteEvents{
    data class OnTitleChange(val title: String): AddNoteEvents()
    data class OnAuthorChange(val author: String): AddNoteEvents()
    data class OnTChaptsChange(val totChap: Int): AddNoteEvents()
    data class OnRdChaptsChange(val rdChap: Int): AddNoteEvents()
}