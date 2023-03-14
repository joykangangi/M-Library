package com.example.m_library.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.ui.screens.book_detail.BookDetailState
import com.example.m_library.ui.screens.book_detail.BookState
import com.example.m_library.ui.screens.book_detail.EditBookEvents
import com.example.m_library.util.dateToLocal
import com.example.m_library.util.localDateToDate
import com.example.m_library.util.safeToInt
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

    private var _currentBookId: Long? = null
    val currentBookId = _currentBookId

    private val _bookState: MutableState<BookState> = mutableStateOf(BookState())
    val bookState: State<BookState> = _bookState


    /**
     * Add note screen Util
     */


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

            is EditBookEvents.SaveBook -> {
                    addBook(
                        Book(
                            id = _currentBookId,
                            author = _bookDetailState.value.author,
                            title = _bookDetailState.value.title,
                            totalChapters = _bookDetailState.value.totalChapters.safeToInt(),
                            currentChapter = _bookDetailState.value.readChapters.safeToInt(),
                            readStatus = _bookDetailState.value.selectedStatus,
                            readByDate = localDateToDate(_bookDetailState.value.readByDate)
                        )
                    )
                }
            is EditBookEvents.DeleteBook -> {
                _bookState.value.book?.let { deleteBook(book = it) }
                }
            else -> {}
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

    /*



    private fun sortByDates(): Flow<List<Book>> = bookRepository.sortByDate()


    //NewWord
    private val newWords: Flow<List<NewWord>> = bookRepository.getAllNewWords()

    fun addNewWord(newWord: NewWord) = viewModelScope.launch {
        if (newWord.id == null){
            bookRepository.insertNewWord(newWord = newWord)
        }
        else {
            bookRepository.updateWord(newWord = newWord)
        }
    }

    fun deleteNewWord(newWord: NewWord) = viewModelScope.launch {
        bookRepository.deleteWord(newWord = newWord)
    }

    fun getNewWord(id: Long) = viewModelScope.launch {
        bookRepository.getNewWord(id = id)
    }

     */
}