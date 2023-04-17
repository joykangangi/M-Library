package com.example.m_library.model

import androidx.compose.runtime.Stable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Stable
@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String,
    @ColumnInfo val readStatus: ReadingStatus,
    @ColumnInfo(name = "read_by_date") val readByDate: Date,
    @ColumnInfo(name = "current_chapter") val currentChapter:Int=0,
    @ColumnInfo(name = "total_chapters") val totalChapters:Int=0
)

enum class ReadingStatus {
    TO_READ,
    READING,
    COMPLETED
}