package com.example.m_library.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreen(
    val route: String, //route
    val title: String,
    val icon: ImageVector
) {
    //All Books/Home Page
    object MyBooks: BottomScreen(route = "my_books", title = "MyBooks", icon = Icons.Outlined.List)

    //Add Book
    object AddBook: BottomScreen(route = "add_book", title = "Add Book", icon = Icons.Outlined.Add)

    //New Word
    object NewWord: BottomScreen(route = "new_word", title = "New Words", icon = Icons.Outlined.Star)

}