package com.example.m_library.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.m_library.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomBottomNav() {

    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true))}

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
                        bottomBarState = bottomBarState.value,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = { FabButton(bottomBarState = bottomBarState.value){
            navController.navigate(Screen.AddBook.route)
        } },
    ) {
        NavGraph(navController = navController)
    }
}

@Composable
fun FabButton(bottomBarState: Boolean,onFabClick:() -> Unit) {
    if (bottomBarState) {
        FloatingActionButton(
            onClick = onFabClick,
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
}

@Composable
fun CustomBottomBar(
    navController: NavHostController,
    bottomBarState: Boolean,
    currentDestination: NavDestination?
) {
    val bottomScreens: List<BottomScreen> = listOf(
        BottomScreen.MyBooks,
        BottomScreen.Stats
    )

    Log.i("CUSTOM BOTTOM NAV - Bottom Bar State", "$bottomBarState")

    AnimatedVisibility(
        visible = bottomBarState,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BottomNavigation {

            bottomScreens.forEach { screen ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                BottomNavigationItem(
                    icon =  {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    },
                    label =  { Text(text = screen.title, color = MaterialTheme.colors.onPrimary)},
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id){ saveState = true}
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

