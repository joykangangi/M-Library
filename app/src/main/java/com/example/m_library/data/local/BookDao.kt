package com.example.m_library.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Long): Book?
}

@Dao
interface NewWordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWord(newWord: NewWord)

    @Update
    suspend fun updateWord(newWord: NewWord)

    @Delete
    suspend fun deleteWord(newWord: NewWord)

    @Query("SELECT * FROM new_words")
    fun getAllNewWords(): Flow<List<NewWord>>

    @Query("SELECT * FROM new_words WHERE id = :id")
    suspend fun getNewWordById(id: Long): NewWord
}