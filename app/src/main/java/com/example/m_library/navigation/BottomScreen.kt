package com.example.m_library.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    //All Books/Home Page
    object MyBooks: BottomScreen(route = "my_books", title = "MyBooks", icon = Icons.Filled.List)

    object Stats: BottomScreen(route = "book_stats", title = "Stats", icon = Icons.Filled.Settings)

}

sealed class Screen(val route: String) {
    object BookDetailScreen: Screen("detail")
    object BookEditScreen: Screen("edit")
    object AddBook: BottomScreen(route = "add_book", title = "Add Book", icon = Icons.Filled.AddCircle)
}