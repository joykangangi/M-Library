package com.example.m_library.app.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

enum class ReadStatus {
    ToRead,
    Reading,
    Completed
}

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val title: String = "",
    val author: String = "",
    val readStatus: ReadStatus = ReadStatus.ToRead,
    @ColumnInfo(name = "read_by_date") val readByDate: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "current_chapter") val currentChapter: Int = 0,
    @ColumnInfo(name = "total_chapters") val totalChapters: Int = 0
)

@Entity(tableName = "new_words")
data class NewWord(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val newWord: String,
    @ColumnInfo val meaning: String
)