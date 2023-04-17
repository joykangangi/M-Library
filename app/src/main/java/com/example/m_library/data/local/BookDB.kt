package com.example.m_library.data.local

import androidx.room.*
import com.example.m_library.converters.RoomConverters
import com.example.m_library.model.Book

@Database(entities = [Book::class], version = 6, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class BookDB: RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
       const val DATABASE_NAME = "book_db"
    }
}