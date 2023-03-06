package com.example.m_library.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "book_entity")
data class BookEntity(
@PrimaryKey(autoGenerate = true) @ColumnInfo val id: Int,
@ColumnInfo val bookTitle: String,
@ColumnInfo val bookAuthor: String,
@ColumnInfo val bookLabel: Boolean,
@ColumnInfo val readStatus: String,
@ColumnInfo val newWords: String,
@ColumnInfo var readDate: Date? = null
)
