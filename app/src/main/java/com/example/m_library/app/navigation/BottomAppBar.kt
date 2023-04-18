package com.example.m_library.app.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.m_library.app.theme.mediumPadding
import com.example.m_library.app.theme.smallPadding
import com.example.m_library.destinations.BookStatsRouteDestination
import com.example.m_library.destinations.BooksRouteDestination
import com.example.m_library.destinations.EditBookRouteDestination

private data class BottomAppBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

private val screens = listOf(
    BottomAppBarItem(
        BooksRouteDestination.route,
        "Books",
        Icons.Default.List,
    ),

    BottomAppBarItem(
        EditBookRouteDestination.route,
        "Edit Book",
        Icons.Default.Add,
    ),
    BottomAppBarItem(
        BookStatsRouteDestination.route,
        "Stats",
        Icons.Default.Settings,
    ),
)

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // Subscribe to navBackStackEntry, required to get current route
    val navStackBackEntry by navController.currentBackStackEntryAsState()

    val showBottomBar by remember(navStackBackEntry?.destination) {
        derivedStateOf {
            when (navStackBackEntry?.destination?.route) {
                BooksRouteDestination.route, BookStatsRouteDestination.route -> true
                else -> false
            }
        }
    }

    val onClick = remember {
        { screen: BottomAppBarItem ->
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    val selected = remember {
        { screen: BottomAppBarItem ->
            navStackBackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true
        }
    }

    if (showBottomBar) {
        BottomAppBar(
            modifier = modifier,
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        screens.forEach { screen ->
                            if (screen.route == EditBookRouteDestination.route) {
                                FloatingActionButton(
                                    onClick = {
                                        navController.navigate(screen.route)
                                    },
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.background,
                                    elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp),
                                    content = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.title
                                        )
                                    }
                                )
                            } else {
                                BottomAppBarItem(
                                    title = screen.title,
                                    icon = screen.icon,
                                    selected = selected(screen),
                                    onClick = {
                                        onClick(screen)
                                    }
                                )
                            }
                        }
                    },
                )
            }
        )
    }
}

@Composable
private fun BottomAppBarItem(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(
                horizontal = mediumPadding(),
                vertical = smallPadding(),
            )
            .clip(RoundedCornerShape(mediumPadding())),
        content = {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                color = if (selected) {
                    MaterialTheme.colorScheme.primary
                } else MaterialTheme.colorScheme.onSurface
            )
        }
    )
}