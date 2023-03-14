package com.example.m_library.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.m_library.ui.screens.add_book.AddBook
import com.example.m_library.ui.screens.book_detail.BookDetail
import com.example.m_library.ui.screens.book_detail.EditBook
import com.example.m_library.ui.screens.my_books.AllMyBooks
import com.example.m_library.ui.screens.stats.BookStats
import com.example.m_library.viewmodel.BookViewModel
import kotlin.properties.Delegates


@Composable
fun NavGraph(
    navController: NavHostController,
    bookViewModel: BookViewModel
) {
    var id by Delegates.notNull<Long>()
    NavHost(
        navController = navController,
        startDestination = BottomScreen.MyBooks.route
    ) {
        composable(route = BottomScreen.MyBooks.route) {
            AllMyBooks(
                onClickBook = {
                    id = it.id!!
                    navController.navigate(Screen.BookDetailScreen.route + "/${it.id}")
                }, bookViewModel = bookViewModel
            )
        }

        composable(route = BottomScreen.AddBook.route) {
            AddBook(
                onCloseDialog = { navController.navigate(BottomScreen.MyBooks.route) },
                bookViewModel = bookViewModel
            )
        }

        composable(route = BottomScreen.Stats.route) {
           BookStats(viewModel = bookViewModel)
        }
        composable(route = Screen.BookDetailScreen.route + "/{id}") {
            BookDetail(
                bookViewModel = bookViewModel,
                onBackClicked = {
                    navController.navigate(BottomScreen.MyBooks.route)
                },
                onEditClick = {
                    navController.navigate(Screen.BookEditScreen.route + "/{id}")
                },
                id = id
            )
        }

        composable(route = Screen.BookEditScreen.route + "/{id}") {
            EditBook(bookViewModel = bookViewModel, onCloseEdit = {
                navController.navigate(Screen.BookDetailScreen.route + "/{id}")
            }, id = id)
        }
    }
}