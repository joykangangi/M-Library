package com.example.m_library.ui.screens.book_detail

import com.example.m_library.model.Book
import java.time.LocalDate
import kotlin.reflect.typeOf

data class BookDetailState(
    val selectedStatus: Int = 0,
    val readByDate: LocalDate = LocalDate.now(),
    val title: String = "",
    val author: String = "",
    val totalChapters: String = "",
    val readChapters: String = "",
    val errorMessage: String = "",
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