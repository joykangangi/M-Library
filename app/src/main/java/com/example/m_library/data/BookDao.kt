package com.example.m_library.data

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

    @Query("SELECT * from books WHERE id = :id")
    fun getAllBooks(): Flow<List<Book>>
}

@Dao
interface NewWordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWord(newWord: NewWord)

    @Update
    suspend fun updateWord(newWord: NewWord)

    @Delete
    suspend fun deleteWord(newWord: NewWord)

    @Query("SELECT * from new_words WHERE id = :id")
    fun getAllNewWords(): Flow<List<NewWord>>
}