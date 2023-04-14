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

private const val duration = 1000
private val slideEffect = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
private val popupEffect = tween<IntOffset>(
    durationMillis = 2000,
    easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimNavGraph(
    navController: NavHostController,
    viewModel: BookViewModel = hiltViewModel()
) {
    val slideEffect = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
    val popupEffect = tween<IntOffset>(
        durationMillis = 2000,
        easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
    )

    AnimatedNavHost(navController = navController, startDestination = BottomScreen.MyBooks.route) {
        navigation(
            route = BottomScreen.MyBooks.route,
            content = {
                AllMyBooks(
                    bookViewModel = viewModel,
                    onClickBook = {
                        navController.navigate(Screen.BookDetailScreen.route + "/${it.id}")
                    }
                )
            }
        )

        composable(
            route = Screen.AddBook.route,
            enterTransition = {
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = popupEffect)
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -1000 }, animationSpec = popupEffect)
            },
            popEnterTransition = {
                slideInVertically(initialOffsetY = { -1000 }, animationSpec = popupEffect)
            },
            popExitTransition = {
                slideOutVertically(targetOffsetY = { 1000 }, animationSpec = popupEffect)
            }
        ) {
            AddBook(
                bookViewModel = hiltViewModel(),
                onCloseDialog = {
                    navController.navigate(BottomScreen.MyBooks.route) {
                        popUpTo(BottomScreen.MyBooks.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = BottomScreen.Stats.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = slideEffect)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = slideEffect)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = slideEffect)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = slideEffect)
            }
        ) {
            BookStats()
        }
        composable(
            route = Screen.BookDetailScreen.route + "/{id}",
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = slideEffect)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = slideEffect)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = slideEffect)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = slideEffect)
            }
        ) {
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

        composable(
            route = Screen.BookEditScreen.route + "/{id}",
            enterTransition = {
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = popupEffect)
            },
            exitTransition = {
                slideOutVertically(targetOffsetY = { -1000 }, animationSpec = popupEffect)
            },
            popEnterTransition = {
                slideInVertically(initialOffsetY = { -1000 }, animationSpec = popupEffect)
            },
            popExitTransition = {
                slideOutVertically(targetOffsetY = { 1000 }, animationSpec = popupEffect)
            }
        ) {
            EditBook(
                bookViewModel = viewModel,
                onCloseEdit = {
                    navController.navigate(Screen.BookDetailScreen.route + "/{id}")
                }
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.navigation(
    route: String,
    content: @Composable() (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit),
    isVertical: Boolean = false,
) {
    composable(
        route = route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { duration }, animationSpec = slideEffect)
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -duration }, animationSpec = slideEffect)
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -duration }, animationSpec = slideEffect)
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { duration }, animationSpec = slideEffect)
        },
        content = content,
    )
}