package com.example.m_library.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.m_library.ui.screens.add_book.AddBook
import com.example.m_library.ui.screens.book_detail.BookDetail
import com.example.m_library.ui.screens.book_detail.EditBook
import com.example.m_library.ui.screens.my_books.AllMyBooks
import com.example.m_library.ui.screens.stats.BookStats
import com.example.m_library.viewmodel.BookViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimNavGraph(
    navController: NavHostController,
    viewModel: BookViewModel = hiltViewModel()
) {

    AnimatedNavHost(navController = navController, startDestination = BottomScreen.MyBooks.route) {
        setComposable(route = BottomScreen.MyBooks.route) {
            AllMyBooks(bookViewModel = viewModel, onClickBook = {
                navController.navigate(Screen.BookDetailScreen.route + "/${it.id}")
            })
        }

        setComposable(route = Screen.AddBook.route) {
            AddBook(bookViewModel = hiltViewModel(), onCloseDialog = {
                navController.navigate(BottomScreen.MyBooks.route) {
                    popUpTo(BottomScreen.MyBooks.route)
                }
            })
        }

        setComposable(route = BottomScreen.Stats.route) {
            BookStats()
            navController.popBackStack()
        }

        setComposable(route = Screen.BookDetailScreen.route + "/{id}") {
            BookDetail(
                bookViewModel = viewModel,
                onBackClicked = {
                    navController.navigate(BottomScreen.MyBooks.route) {
                        popUpTo(BottomScreen.MyBooks.route)
                    }
                },
                onEditClick = {
                    navController.navigate(Screen.BookEditScreen.route + "/{id}")
                }
            )
        }

        setComposable(route = Screen.BookEditScreen.route + "/{id}") {
            EditBook(
                bookViewModel = viewModel,
                onCloseEdit = {
                    navController.navigate(Screen.BookDetailScreen.route + "/{id}") {
                        popUpTo(route = Screen.BookDetailScreen.route)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.setComposable(
    route: String,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    val slideEffect = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
    val popupEffect = tween<IntOffset>(
        durationMillis = 500,
        easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
    )

    val offset = 300

    return composable(
        route = route,
        enterTransition = {
            if (route == Screen.AddBook.route) {
                slideInVertically(initialOffsetY = { offset }, animationSpec = popupEffect)
            } else
                slideInHorizontally(initialOffsetX = { offset }, animationSpec = slideEffect)
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -offset }, animationSpec = slideEffect)
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -offset }, animationSpec = slideEffect)
        },
        popExitTransition = {
            if (route == Screen.AddBook.route) {
                slideOutVertically(targetOffsetY = { offset }, animationSpec = popupEffect)
            } else
                slideOutHorizontally(targetOffsetX = { offset }, animationSpec = slideEffect)
        },
        content = content
    )
}