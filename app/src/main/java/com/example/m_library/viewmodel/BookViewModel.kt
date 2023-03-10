package com.example.m_library.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.model.NewWord
import com.example.m_library.ui.screens.add_book.components.AddBookEvents
import com.example.m_library.ui.screens.add_book.components.AddBookState
import com.example.m_library.ui.screens.my_books.BookListEvents
import com.example.m_library.ui.screens.my_books.BookListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject constructor(
    private val bookRepository: BookRepository
): ViewModel(){

    //val bookListFlow: Flow<List<Book>> = bookRepository.getAllBooks()

    private val _addBookState: MutableState<AddBookState> = mutableStateOf(AddBookState())
    val addBookState: State<AddBookState> = _addBookState

    private val _bookListState: MutableState<BookListState> = mutableStateOf(BookListState())
    val bookListState: State<BookListState> = _bookListState

    init {
        getBooks()
    }



    /**
     * Add note screen Util
     */
    fun getAddEvent(event: AddBookEvents) {
        when (event) {
            is AddBookEvents.OnAuthorChange -> {
                _addBookState.value = _addBookState.value.copy(author = event.author)

            }
            is AddBookEvents.OnRdChaptsChange -> {
                _addBookState.value = _addBookState.value.copy(readChapters = event.rdChap)
            }
            is AddBookEvents.OnTChaptsChange -> {
                _addBookState.value = _addBookState.value.copy(totalChapters = event.totChap)
            }
            is AddBookEvents.OnTitleChange -> {
                _addBookState.value = _addBookState.value.copy(title = event.title)
            }
            is AddBookEvents.OnSelectChange -> {
                _addBookState.value = _addBookState.value.copy(selectedStatus = event.selectedIndex)
            }
            is AddBookEvents.OnDateChange -> {
                _addBookState.value = _addBookState.value.copy(readByDate = event.dateChange)
            }
        }
    }

    /**
     * All Books Util
     */

    fun getBooksEvent(event: BookListEvents) = viewModelScope.launch {
        when(event) {
            is BookListEvents.DeadLineChanged -> {
                _bookListState.value = _bookListState.value.copy(isDeadLine = !bookListState.value.isDeadLine)
                if (_bookListState.value.isDeadLine) {
                    _bookListState.value= _bookListState.value.copy(bookList = sortByDates().first())
                }
                else {
                    _bookListState.value = _bookListState.value.copy(bookList = bookRepository.getAllBooks().first() )
                }
            }
        }
    }

    private fun getBooks() = viewModelScope.launch{
        _bookListState.value = _bookListState.value.copy(bookList = bookRepository.getAllBooks().first() )
        if (_bookListState.value.isDeadLine) {
            sortByDates()
        }
    }




    fun addBook(book: Book) = viewModelScope.launch {
        if (book.id == null) {
            bookRepository.insertBook(book = book)
        }
        else {
            bookRepository.updateBook(book = book)
        }
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        bookRepository.deleteBook(book = book)
    }

    fun getBook(id: Long) = viewModelScope.launch {
        bookRepository.getBook(id = id)
    }

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
}