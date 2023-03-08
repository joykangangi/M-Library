package com.example.m_library.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String,
    @ColumnInfo val readStatus: ReadingStatus,
    @ColumnInfo(name = "read_by_date") var readByDate: Date? = null,
    @ColumnInfo var currentChapter:Int = 0,
    @ColumnInfo var totalChapters:Int = 0
)

enum class ReadingStatus{
    READING,
    TO_READ,
    COMPLETED
}

@Entity(tableName = "new_words")
data class NewWord(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val newWord: String,
    @ColumnInfo val meaning: String
)
