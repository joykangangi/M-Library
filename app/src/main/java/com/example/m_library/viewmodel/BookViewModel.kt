package com.example.m_library.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.ui.screens.book_detail.BookDetailState
import com.example.m_library.ui.screens.book_detail.EditBookEvents
import com.example.m_library.util.dateToLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    val bookListFlow: Flow<List<Book>> = bookRepository.getAllBooks()
    val finishedListFlow: Flow<List<Book>> = bookRepository.finishedBooks()
    val readingListFlow: Flow<List<Book>> = bookRepository.readingBooks()

    private val _bookDetailState: MutableState<BookDetailState> = mutableStateOf(BookDetailState())
    val bookDetailState: State<BookDetailState> = _bookDetailState


    fun editEvent(event: EditBookEvents) {
        when (event) {
            is EditBookEvents.OnAuthorChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(author = event.author)
            }
            is EditBookEvents.OnDateChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(readByDate = event.dateChange)
            }
            is EditBookEvents.OnRdChaptsChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(readChapters = event.rdChap)
            }
            is EditBookEvents.OnSelectChange -> {
                _bookDetailState.value =
                    _bookDetailState.value.copy(selectedStatus = event.selectedIndex)
            }
            is EditBookEvents.OnTChaptsChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(totalChapters = event.totChap)
            }
            is EditBookEvents.OnTitleChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(title = event.title)
            }
        }
    }

    /**
     * All Books Util
     */


    fun setSelectedBook(book: Book) {
        _bookDetailState.value = _bookDetailState.value.copy(
            title = book.title,
            author = book.author,
            readByDate = dateToLocal(book.readByDate),
            totalChapters = book.totalChapters.toString(),
            readChapters = book.currentChapter.toString(),
            selectedStatus = book.readStatus
        )
    }


    fun addBook(book: Book) = viewModelScope.launch {
        bookRepository.insertBook(book = book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        bookRepository.deleteBook(book = book)
    }

    fun validInput(): Boolean {
        return !(_bookDetailState.value.author.isBlank() ||
                _bookDetailState.value.readByDate.toString().isBlank()
                || _bookDetailState.value.title.isBlank() || _bookDetailState.value.readChapters.isBlank() ||
                _bookDetailState.value.totalChapters.isBlank() ||
                _bookDetailState.value.selectedStatus.toString().isBlank())
    }
}