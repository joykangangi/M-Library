package com.example.m_library.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.ui.screens.book_detail.BookDetailState
import com.example.m_library.ui.screens.book_detail.EditBookEvents
import com.example.m_library.util.localDateToDate
import com.example.m_library.util.safeToInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val bookRepository: BookRepository
    ) : ViewModel() {

    private val _bookDetailState = MutableStateFlow(BookDetailState())
    val bookDetailState: StateFlow<BookDetailState> = _bookDetailState.asStateFlow()

    private val currentId: Long? = null




    fun editEvent(event: EditBookEvents) {
        when (event) {
            is EditBookEvents.OnAuthorChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(author = event.author)

            }
            is EditBookEvents.OnDateChange -> {

                //  _bookDetailState.emit(_bookDetailState.first().copy(readByDate = event.dateChange))
                _bookDetailState.value = _bookDetailState.value.copy(readByDate = event.dateChange)

            }
            is EditBookEvents.OnRdChaptsChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(readChapters = event.rdChap)
            }
            is EditBookEvents.OnSelectChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(selectedStatus = event.selectedIndex)
            }
            is EditBookEvents.OnTChaptsChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(totalChapters = event.totChap)
            }
            is EditBookEvents.OnTitleChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(title = event.title)
            }
            EditBookEvents.SaveBook -> {
                viewModelScope.launch {
                    upsertBook(book =
                    Book(
                        id = currentId,
                        readByDate = localDateToDate(_bookDetailState.value.readByDate),
                        currentChapter = _bookDetailState.value.readChapters.safeToInt(),
                        totalChapters = _bookDetailState.value.totalChapters.safeToInt(),
                        readStatus = _bookDetailState.value.selectedStatus,
                        author = _bookDetailState.value.author,
                        title = _bookDetailState.value.title
                    )
                    )
                }

            }
        }
    }

    fun getBook(id: Long) = viewModelScope.launch {
        bookRepository.getBook(id = id)
    }

    private fun upsertBook(book: Book) = viewModelScope.launch {
        bookRepository.insertBook(book = book)
    }



    fun validInput(): Boolean {
        return !(_bookDetailState.value.author.isBlank() ||
                _bookDetailState.value.readByDate.toString().isBlank()
                || _bookDetailState.value.title.isBlank() || _bookDetailState.value.readChapters.isBlank() ||
                _bookDetailState.value.totalChapters.isBlank() ||
                _bookDetailState.value.selectedStatus.toString().isBlank())
    }
}
