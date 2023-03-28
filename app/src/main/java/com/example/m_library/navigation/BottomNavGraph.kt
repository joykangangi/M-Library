package com.example.m_library.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.m_library.ui.screens.add_book.AddBook
import com.example.m_library.ui.screens.book_detail.BookDetail
import com.example.m_library.ui.screens.book_detail.EditBook
import com.example.m_library.ui.screens.my_books.AllMyBooks
import com.example.m_library.ui.screens.stats.BookStats
import com.example.m_library.viewmodel.BookViewModel
import dagger.hilt.android.scopes.ViewModelScoped


@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: BookViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = BottomScreen.MyBooks.route
    ) {

        composable(route = BottomScreen.MyBooks.route) {_->
            AllMyBooks(
                bookViewModel = viewModel,
                onClickBook = {
                    navController.navigate(Screen.BookDetailScreen.route + "/${it.id}")
                    Log.i("Bottom Nav","The screen number is: ${Screen.BookDetailScreen.route+ it.id} ")
                }
            )
        }

        composable(route = Screen.AddBook.route) {
            AddBook(
                bookViewModel = hiltViewModel(),
                onCloseDialog = {
                    navController.navigate(BottomScreen.MyBooks.route) {
                        popUpTo(BottomScreen.MyBooks.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = BottomScreen.Stats.route) {
            BookStats()
        }
        composable(route = Screen.BookDetailScreen.route + "/{id}") {
            BookDetail(
                bookViewModel = viewModel,
                onBackClicked = {
                    navController.navigate(BottomScreen.MyBooks.route)
                },
                onEditClick = {
                    navController.navigate(Screen.BookEditScreen.route + "/{id}")
                }
            )
        }

        composable(route = Screen.BookEditScreen.route + "/{id}") {
            EditBook(
                bookViewModel = viewModel,
                onCloseEdit = {
                navController.navigate(Screen.BookDetailScreen.route + "/{id}")
            })
        }
    }
}