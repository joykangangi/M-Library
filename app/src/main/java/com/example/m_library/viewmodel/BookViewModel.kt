package com.example.m_library.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.ui.screens.add_book.components.EditBookEvents
import com.example.m_library.ui.screens.book_detail.AddFormState
import com.example.m_library.ui.screens.book_detail.BookDetailState
import com.example.m_library.ui.screens.my_books.BookState
import com.example.m_library.ui.screens.stats.ExpandedEvents
import com.example.m_library.ui.screens.stats.ExpandedState
import com.example.m_library.util.dateToLocal
import com.example.m_library.util.localDateToDate
import com.example.m_library.util.safeToInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    val allList = bookRepository.getAllBooks()
    val finishedList = bookRepository.finishedBooks()
    val readingList = bookRepository.readingBooks()
    val futureReads = bookRepository.futureReads()


    private val _expandedState = MutableStateFlow(ExpandedState())
    val expandedState: StateFlow<ExpandedState> = _expandedState.asStateFlow()


    private val _bookState = MutableStateFlow(BookState())
    val bookState: StateFlow<BookState> = _bookState.asStateFlow()

    private val _bookDetailState = MutableStateFlow(BookDetailState())
    val bookDetailState: StateFlow<BookDetailState> = _bookDetailState.asStateFlow()

    private var currentId: Long? = null

    private var vminstance = 1

    init {
        Log.i("BookViewModel", "Book View Model Instance = ${vminstance++}")
    }

    fun onCardArrowClicked(expandedEvents: ExpandedEvents) {
        when (expandedEvents) {
            ExpandedEvents.ExpandFinish -> {
                _expandedState.value =
                    _expandedState.value.copy(isFinishExpanded = !_expandedState.value.isFinishExpanded)
            }
            ExpandedEvents.ExpandReading -> {
                _expandedState.value =
                    _expandedState.value.copy(isReadingExpanded = !_expandedState.value.isReadingExpanded)
            }
            ExpandedEvents.ExpandToRead -> {
                _expandedState.value =
                    _expandedState.value.copy(isToReadExpanded = !_expandedState.value.isToReadExpanded)
            }
        }

    }


    fun editEvent(event: EditBookEvents) {
        when (event) {
            is EditBookEvents.OnAuthorChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(author = event.author)
            }
            is EditBookEvents.OnDateChange -> {
                _bookDetailState.value = _bookDetailState.value.copy(readByDate = event.dateChange)

            }
            is EditBookEvents.OnRdChaptsChange -> {
//                viewModelScope.launch {
//                    _bookDetailState.emit(_bookDetailState.first().copy(readChapters = event.rdChap))
//                }
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
            EditBookEvents.SaveBook -> {
                viewModelScope.launch {
                    upsertBook(
                        book =
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
            EditBookEvents.RestoreDetails -> {
                //immediately the user adds a new book that VM instance is destroyed,
                // yes they will find a clear screen when they want to add another book
                // but, if the want to edit a book, the edit screen book, will have empty fields
                // because the viewmodel instance was destroyed.
                // "but you can use the values from `bookstate`?" you might wonder
                // if we do this we'll have to add code for event and state management similar to this `fun getEvent`
                // but the state will be `bookstate` that will cause alot of redundancy and more errors/complexity
                // so when the user goes to edit, we copy the values of bookstate to detail state.
                //remember, detail state at this point is empty
                // (this is actually what I wanted to do in the beginning but I didnt have the clarity then)
                _bookDetailState.value = BookDetailState(
                    selectedStatus = _bookState.value.book?.readStatus ?: 0,
                    readByDate = dateToLocal(_bookState.value.book?.readByDate),
                    title = _bookState.value.book?.title ?: "",
                    author = _bookState.value.book?.author ?: "",
                    totalChapters = _bookState.value.book?.totalChapters.toString(),
                    readChapters = _bookState.value.book?.currentChapter.toString()
                )
            }
        }
    }

    //gives value to the bookstate, that will be called in the detail screen
    fun setSelectedBook(book: Book) {
        viewModelScope.launch {
            _bookState.emit(BookState(book = book))
        }
        Log.i("VM-Book Value", "$book")
        Log.i("VM-BookState Value", "${_bookState.value}")
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

    fun deleteBook(book: Book) = viewModelScope.launch {
        bookRepository.deleteBook(book = book)
    }


    fun validText(input: String): Boolean {
        return (input.isNotBlank() && input.length > 4)
    }

    fun validChapter(readChp: Int, totChap: Int): Boolean {
        return readChp < totChap

    }

    fun validateInput(): Boolean {
        val validAuthor =
            (_bookDetailState.value.author.isNotBlank() && _bookDetailState.value.author.length > 5)
        val validTitle =
            (_bookDetailState.value.title.isNotBlank() && _bookDetailState.value.title.length > 5)
        val validReadChapters =
            _bookDetailState.value.readChapters < _bookDetailState.value.totalChapters
                    &&
                    _bookDetailState.value.readChapters.isNotBlank() && _bookDetailState.value.totalChapters.isNotBlank()
        val formValid = validAuthor || validTitle || validReadChapters
        return formValid
    }
}