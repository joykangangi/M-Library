package com.example.m_library.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.m_library.R
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomBottomNav() {

    val navController: NavHostController = rememberAnimatedNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // Subscribe to navBackStackEntry, required to get current route
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    when (currentDestination?.route) {
        BottomScreen.MyBooks.route -> bottomBarState.value = true
        BottomScreen.Stats.route -> bottomBarState.value = true
        else -> bottomBarState.value = false
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        bottomBar = {
            if (bottomBarState.value) {
                BottomAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    cutoutShape = CircleShape
                ) {
                    CustomBottomBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (bottomBarState.value) {
                FabButton {
                    navController.navigate(Screen.AddBook.route)
                }
            }
        },
    ) {
        AnimNavGraph(navController = navController)
    }
}

@Composable
fun FabButton(modifier: Modifier = Modifier, onFabClick: () -> Unit) {
    val fabClick = remember {
        { onFabClick() }
    }

    FloatingActionButton(
        modifier = modifier,
        onClick = fabClick,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.background,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add_book)
        )
    }
}


@Composable
fun CustomBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    val bottomScreens: List<BottomScreen> = listOf(
        BottomScreen.MyBooks,
        BottomScreen.Stats
    )


    BottomNavigation {

        bottomScreens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val onScreenClicked = remember {
                { navController.navigate(screen.route) }
            }
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        tint = MaterialTheme.colors.onPrimary
                    )
                },
                label = { Text(text = screen.title, color = MaterialTheme.colors.onPrimary) },
                selected = isSelected,
                onClick = onScreenClicked
            )
        }
    }
}


