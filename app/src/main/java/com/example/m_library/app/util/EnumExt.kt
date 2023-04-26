package com.example.m_library.app.util


/**
 * The main difference between accessing the .name property of an enum constant directly
 * and using a custom extension property like formattedName
 * is that the former returns the original name of the enum constant as defined in the enum class,
 * while the latter returns a formatted name based on the custom logic defined in the getter of the extension property.
 * e.g ToRead->To Read
 */
val <T : Enum<T>> Enum<T>.formattedName: String
    get() {
        val builder = StringBuilder()
        name.toCharArray().forEach { char ->
            if (char.isUpperCase()) builder.append(" ")
            builder.append(char)
        }
        return builder.toString().trim()
    }