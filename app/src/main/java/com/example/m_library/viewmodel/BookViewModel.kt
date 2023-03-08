package com.example.m_library.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.m_library.data.BookRepository
import com.example.m_library.model.Book
import com.example.m_library.model.NewWord
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel
@Inject constructor(
    private val bookRepository: BookRepository
): ViewModel(){

    private val bookListFlow: Flow<List<Book>> = bookRepository.getAllBooks()

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

    fun sortByDates(): Flow<List<Book>> = bookRepository.sortByDate()

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