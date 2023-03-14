package com.example.m_library.util

import java.io.Serializable
import java.time.*
import java.util.*


fun localDateToDate(readDate: LocalDate?): Date {
    val localDateTime = LocalDateTime.of(readDate, LocalDateTime.MIN.toLocalTime())
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}

fun dateToLocal(readDate: Date?): LocalDate {
    val instant = readDate?.let { Instant.ofEpochMilli(it.time) }
    val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
    return zonedDateTime.toLocalDate()
}



