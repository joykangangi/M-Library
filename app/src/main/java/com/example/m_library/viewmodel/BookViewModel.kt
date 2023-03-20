package com.example.m_library.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.ui.screens.book_detail.BookDetailState
import com.example.m_library.ui.screens.book_detail.EditBookEvents
import com.example.m_library.ui.screens.my_books.BookState
import com.example.m_library.util.localDateToDate
import com.example.m_library.util.safeToInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {


    val allList = bookRepository.getAllBooks()
    val finishedList = bookRepository.finishedBooks()
    val readingList = bookRepository.readingBooks()

    private val _bookState = MutableStateFlow(BookState())
    val bookState: StateFlow<BookState> = _bookState.asStateFlow()


    fun setSelectedBook(book: Book) {
        _bookState.value = _bookState.value.copy(book = book)
        Log.i("VM-Book Value","$book")
    }



    fun deleteBook(book: Book) = viewModelScope.launch {
        bookRepository.deleteBook(book = book)
    }

}