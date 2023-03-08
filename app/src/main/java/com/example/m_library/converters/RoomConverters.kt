package com.example.m_library.converters

import androidx.room.TypeConverter
import java.util.Date

class RoomConverters() {

    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun longToDate(timeLong: Long): Date {
        return Date(timeLong)
    }
}