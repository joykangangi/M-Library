package com.example.m_library.app.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

val LocalDate.formatted: String get() = format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))