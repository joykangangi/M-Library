package com.example.m_library.data.local

import android.content.Context
import androidx.room.*
import com.example.m_library.converters.RoomConverters
import com.example.m_library.model.Book
import com.example.m_library.model.NewWord

@Database(entities = [Book::class, NewWord::class], version = 5, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class BookDB: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun newWordDao(): NewWordDao

    companion object {
       const val DATABASE_NAME = "book_db"
    }
}