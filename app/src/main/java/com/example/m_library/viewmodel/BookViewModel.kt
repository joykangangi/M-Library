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
import kotlinx.coroutines.flow.*
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


    private val _bookState = MutableStateFlow(BookState())
    val bookState: StateFlow<BookState> = _bookState.asStateFlow()

    private val _bookDetailState = MutableStateFlow(BookDetailState())
    val bookDetailState: StateFlow<BookDetailState> = _bookDetailState.asStateFlow()

    private var currentId: Long? = null

    private var vminst = 0

    init {
        Log.i("BookViewModel", "Book View Model Instance = ${vminst++}")
    }

//    private val _eventFlow = MutableSharedFlow<EditBookEvents>()
//    val eventFlow = _eventFlow.asSharedFlow()

//    init {
//        //if a book exists
//        savedStateHandle.get<Long>("bookId")?.let { bookId: Long ->
//            if (bookId != -1L) {
//                viewModelScope.launch {
//                    bookRepository.getBook(id = bookId)?.also { book: Book ->
//                        currentId = book.id
//                        _bookDetailState.value = _bookDetailState.value.copy(
//                            author = book.author,
//                            title = book.title,
//                            readByDate = dateToLocal(book.readByDate),
//                            totalChapters = book.totalChapters.toString(),
//                            readChapters = book.currentChapter.toString(),
//                            selectedStatus = book.readStatus
//                        )
//                        _addTextState.value =
//                            AddTextState(headerText = "Edit", buttonText = "Update")
//                    }
//                }
//            }
//        }
//
//    }


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
        }
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


    //gives value to the bookstate, that will be called in the detail screen
    fun setSelectedBook(book: Book) {
        viewModelScope.launch {
            _bookState.emit(BookState(book = book))
        }
        Log.i("VM-Book Value", "$book")
        Log.i("VM-BookState Value", "${_bookState.value}")
    }

    fun getBook(id: Long) = viewModelScope.launch {
        bookRepository.getBook(id = id)
    }


    fun deleteBook(book: Book) = viewModelScope.launch {
        bookRepository.deleteBook(book = book)
    }
}


//class BookViewModelFactory(private val bookRepository: BookRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return BookViewModel(bookRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}