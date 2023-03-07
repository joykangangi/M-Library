package com.example.m_library.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val id: String,
    val title: String,
    val icon: ImageVector
) {
    object MyBooks: Screen(id = "my_books", title = "MyBooks", icon = Icons.Outlined.List)
    object AddBook: Screen(id = "add_book", title = "Add Book", icon = Icons.Outlined.Add)
    object NewWord: Screen(id = "new_word", title = "New Words", icon = Icons.Outlined.Star)

    object Items{
        val list = listOf(
            MyBooks, AddBook, NewWord
        )
    }
}