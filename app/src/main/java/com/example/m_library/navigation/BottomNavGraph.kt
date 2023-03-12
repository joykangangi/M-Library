package com.example.m_library.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.m_library.ui.screens.add_book.AddBook
import com.example.m_library.ui.screens.book_detail.BookDetail
import com.example.m_library.ui.screens.my_books.AllMyBooks
import com.example.m_library.ui.screens.new_words.WordsList
import com.example.m_library.viewmodel.BookViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.MyBooks.route
    ) {
        composable(route = BottomScreen.MyBooks.route) {
            AllMyBooks(
                onClickBook = {
                navController.navigate(Screen.BookDetailScreen.route + "/${it.id}")
            }, bookViewModel = bookViewModel)
        }

        composable(route = BottomScreen.AddBook.route) {
            AddBook(
                onCloseDialog = { navController.navigate(BottomScreen.MyBooks.route) },
                bookViewModel = bookViewModel
            )
        }

        composable(route = BottomScreen.NewWord.route) {
            WordsList()
        }
        composable(route = Screen.BookDetailScreen.route + "/{id}") {
            BookDetail(bookViewModel = bookViewModel, onBackClicked = {
                navController.navigate(BottomScreen.MyBooks.route)
            })
            Log.i("Book DEtail", "book detail nav ")
        }
    }
}