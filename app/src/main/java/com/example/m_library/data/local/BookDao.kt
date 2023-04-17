package com.example.m_library.data.local

import androidx.room.*
import com.example.m_library.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE current_chapter = total_chapters")
    fun finishedBooks(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE current_chapter > 0 AND current_chapter < total_chapters")
    fun currentReads(): Flow<List<Book>>

    @Query("SELECT * FROM BOOKS WHERE current_chapter = 0")
    fun futureReads(): Flow<List<Book>>
}