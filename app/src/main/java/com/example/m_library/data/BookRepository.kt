package com.example.m_library.data

import com.example.m_library.model.Book
import com.example.m_library.data.local.BookDao
import com.example.m_library.model.NewWord
import com.example.m_library.data.local.NewWordDao
import kotlinx.coroutines.flow.Flow

/**
 * Why val not var
 * In the context of a repository class, you typically want to avoid modifying the state
 * of the repository itself, since it's responsible for managing the data in the database.
 * Therefore, it's often appropriate to use val for the private fields in a repository.
 */

class BookRepository(private val bookDao: BookDao, private val newWordDao: NewWordDao) {

    //for books
    fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks()

    suspend fun insertBook(book: Book) = bookDao.insertBook(book = book)

    suspend fun updateBook(book: Book) = bookDao.updateBook(book = book)

    suspend fun deleteBook(book: Book) = bookDao.deleteBook(book = book)

    suspend fun getBook(id: Long) = bookDao.getBookById(id = id)

   fun sortByDate(): Flow<List<Book>> = bookDao.sortDates()

    //for new words
    fun getAllNewWords(): Flow<List<NewWord>> = newWordDao.getAllNewWords()

    suspend fun getNewWord(id: Long) = newWordDao.getNewWordById(id = id)

    suspend fun insertNewWord(newWord: NewWord) = newWordDao.insertNewWord(newWord = newWord)

    suspend fun updateWord(newWord: NewWord) = newWordDao.updateWord(newWord = newWord)

    suspend fun deleteWord(newWord: NewWord) = newWordDao.deleteWord(newWord = newWord)
}