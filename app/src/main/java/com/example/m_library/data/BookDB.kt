package com.example.m_library.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class, NewWord::class], version = 1, exportSchema = false)
abstract class BookDB: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun newWordDao(): NewWordDao

    companion object {
        @Volatile
        private var INSTANCE: BookDB? = null

        fun getDatabase(context: Context): BookDB {
            return INSTANCE?: synchronized(this) {
                Room.databaseBuilder(context, BookDB::class.java,"book_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}