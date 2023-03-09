package com.example.m_library.data.local

import android.content.Context
import androidx.room.*
import com.example.m_library.converters.RoomConverters
import com.example.m_library.model.Book
import com.example.m_library.model.NewWord

@Database(entities = [Book::class, NewWord::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class BookDB: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun newWordDao(): NewWordDao

    companion object {
        @Volatile
        private var INSTANCE: BookDB? = null

        //Todo Provide in DI
        fun getDatabase(context: Context): BookDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, BookDB::class.java,"book_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}