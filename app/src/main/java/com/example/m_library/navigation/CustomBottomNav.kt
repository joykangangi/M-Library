package com.example.m_library.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun CustomBottomNav() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CustomBottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController, modifier = Modifier.padding(it))
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

    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(12.dp)
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
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .height(40.dp)
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
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = bottomScreen.icon,
                contentDescription = bottomScreen.title,
                tint = contentColor
            )
            AnimatedVisibility(visible = isSelected) {
                Text(text = bottomScreen.title, color = contentColor)
            }

        }

    }
}