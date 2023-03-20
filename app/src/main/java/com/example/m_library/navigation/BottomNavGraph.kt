package com.example.m_library.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.m_library.ui.screens.add_book.AddBook
import com.example.m_library.ui.screens.book_detail.BookDetail
import com.example.m_library.ui.screens.book_detail.EditBook
import com.example.m_library.ui.screens.my_books.AllMyBooks
import com.example.m_library.ui.screens.stats.BookStats
import com.example.m_library.viewmodel.AddEditViewModel
import com.example.m_library.viewmodel.BookViewModel


@Composable
fun NavGraph(
    navController: NavHostController
) {
    //var id by Delegates.notNull<Long>()
    NavHost(
        navController = navController,
        startDestination = BottomScreen.MyBooks.route
    ) {
        composable(route = BottomScreen.MyBooks.route) {
            AllMyBooks(
                onClickBook = {
                    navController.navigate(Screen.BookDetailScreen.route + "/${it.id}")
                    Log.i("Bottom Nav","The screen number is: ${Screen.BookDetailScreen.route+ it.id} ")
                }
            )
        }

        composable(route = BottomScreen.AddBook.route) {
            AddBook(
                onCloseDialog = {
                    navController.navigate(BottomScreen.MyBooks.route)
                }
            )
        }

        composable(route = BottomScreen.Stats.route) {
            BookStats()
        }
        composable(route = Screen.BookDetailScreen.route + "/{id}") {
            BookDetail(
                onBackClicked = {
                    navController.navigate(BottomScreen.MyBooks.route)
                },
                onEditClick = {
                    navController.navigate(Screen.BookEditScreen.route + "/{id}")
                }
            )
        }

        composable(route = Screen.BookEditScreen.route + "/{id}") {
            EditBook( onCloseEdit = {
                navController.navigate(Screen.BookDetailScreen.route + "/{id}")
            })
        }
    }
}