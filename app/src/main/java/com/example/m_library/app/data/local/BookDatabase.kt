package com.example.m_library.app.data.local

import androidx.room.*

@Database(entities = [Book::class, NewWord::class], version = 5, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun newWordDao(): NewWordDao
}