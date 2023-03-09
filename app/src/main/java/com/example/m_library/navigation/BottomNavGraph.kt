package com.example.m_library.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.m_library.ui.screens.add_book.AddBook
import com.example.m_library.ui.screens.my_books.AllMyBooks
import com.example.m_library.ui.screens.new_words.WordsList


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.MyBooks.route
    ) {
        composable(route = BottomScreen.MyBooks.route) {
            AllMyBooks(isDeadline = true, onDeadLnClicked = {}, modifier = modifier)
        }

        composable(route = BottomScreen.AddBook.route) {
            AddBook()
        }

        composable(route = BottomScreen.NewWord.route) {
            WordsList()
        }
    }

}