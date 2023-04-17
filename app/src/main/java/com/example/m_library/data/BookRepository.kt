package com.example.m_library.data

import com.example.m_library.data.local.BookDao
import com.example.m_library.model.Book
import kotlinx.coroutines.flow.Flow

/**
 * Why val not var
 * In the context of a repository class, you typically want to avoid modifying the state
 * of the repository itself, since it's responsible for managing the data in the database.
 * Therefore, it's often appropriate to use val for the private fields in a repository.
 */

class BookRepository(private val bookDao: BookDao) {

    //for books
    fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks()

    suspend fun insertBook(book: Book) = bookDao.upsertBook(book = book)

    suspend fun deleteBook(book: Book) = bookDao.deleteBook(book = book)

    fun finishedBooks(): Flow<List<Book>> = bookDao.finishedBooks()
    fun readingBooks(): Flow<List<Book>> = bookDao.currentReads()

    fun futureReads(): Flow<List<Book>> = bookDao.futureReads()
}