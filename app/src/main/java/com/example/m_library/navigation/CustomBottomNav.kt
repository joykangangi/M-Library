package com.example.m_library.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.m_library.viewmodel.BookViewModel

@Composable
fun CustomBottomNav(
    viewModel: BookViewModel
) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomBar(navController = navController)
        }
    ) {
        NavGraph(navController = navController, bookViewModel = viewModel)
        it
    }
}


@Composable
fun CustomBottomBar(
    navController: NavHostController
) {

    val bottomScreens: List<BottomScreen> = listOf(
        BottomScreen.MyBooks,
        BottomScreen.AddBook,
        BottomScreen.NewWord
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Column {
        Divider()

        Row(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            bottomScreens.forEach { screen ->
                BottomNavItem(
                    bottomScreen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(
    bottomScreen: BottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == bottomScreen.route } == true

    val background =
        if (isSelected) MaterialTheme.colors.background.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colors.primary else Color.LightGray

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color = background)
            .clickable(onClick = {
                navController.navigate(bottomScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(all = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(modifier = Modifier.size(35.dp),
                imageVector = bottomScreen.icon,
                contentDescription = bottomScreen.title,
                tint = contentColor
            )
            AnimatedVisibility(visible = isSelected) {
                Text(text = bottomScreen.title, color = contentColor, fontSize = 16.sp)
            }

        }

    }
}