package com.example.m_library.ui.screens.add_book.components

import java.time.LocalDate

data class BookDetailState(
    val selectedStatus: Int = 0,
    val readByDate: LocalDate = LocalDate.now(),
    val title: String = "",
    val author: String = "",
    val totalChapters: String = "",
    val readChapters: String = "",
    val errorMessage: String = "",
    val isEditing: Boolean = false
)

enum class InputType {
    TEXT,
    NUMBER
}


data class InputState(
    val input: String = "",
    val isValid: Boolean = true,
    val errorMessage: String = "",
    val type: InputType
)

data class AddFormState(
    val title: InputState = InputState(type = InputType.TEXT),
    val author: InputState = InputState(type = InputType.TEXT),
    val currentChapt: InputState = InputState(type = InputType.NUMBER),
    val totChpt: InputState = InputState(type = InputType.NUMBER),
    val formValid: Boolean
)