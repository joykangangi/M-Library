package com.example.m_library.app.data.local

import androidx.room.TypeConverter
import java.time.LocalDate


class RoomConverters {

    @TypeConverter
    fun dateToLong(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun longToDate(date: Long?): LocalDate? {
        if (date == null) return null

        return LocalDate.ofEpochDay(date)
    }
}