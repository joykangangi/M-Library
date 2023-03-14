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
    @ColumnInfo val readStatus: Int,
    @ColumnInfo(name = "read_by_date") val readByDate: Date,
    @ColumnInfo(name = "current_chapter") val currentChapter:Int=0,
    @ColumnInfo(name = "total_chapters") val totalChapters:Int=0
) {

    companion object ReadingStatus {
        val choiceList = listOf("TO_READ", "READING", "COMPLETED")
    }
}

@Entity(tableName = "new_words")
data class NewWord(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val newWord: String,
    @ColumnInfo val meaning: String
)
