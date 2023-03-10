package com.example.m_library.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


fun localDateToDate(readDate: LocalDate?): Date {
    val localDateTime = LocalDateTime.of(readDate, LocalDateTime.MIN.toLocalTime())
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}


