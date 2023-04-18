package com.example.m_library.app.util

val <T : Enum<T>> Enum<T>.formattedName: String
    get() {
        val builder = StringBuilder()
        name.toCharArray().forEach { char ->
            if (char.isUpperCase()) builder.append(" ")
            builder.append(char)
        }
        return builder.toString().trim()
    }