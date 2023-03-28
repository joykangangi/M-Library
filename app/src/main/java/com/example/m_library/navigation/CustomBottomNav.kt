package com.example.m_library.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.m_library.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomBottomNav() {

    val navController = rememberNavController()

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                cutoutShape = CircleShape
            ) {
                CustomBottomBar(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = { FabButton{
            navController.navigate(Screen.AddBook.route)
        } },
    ) {
        NavGraph(navController = navController)
    }
}


@Composable
fun FabButton(onFabClick:() -> Unit) {
    FloatingActionButton(
        onClick = onFabClick,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.background,
        elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add_book) )
    }

}

@Composable
fun CustomBottomBar(
    navController: NavHostController
) {

    val bottomScreens: List<BottomScreen> = listOf(
        BottomScreen.MyBooks,
        BottomScreen.Stats
    )

    //val navStackBackEntry by navController.currentBackStackEntryAsState()
  //val currentDestination = navStackBackEntry?.destination

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
                navController = navController
            )
        }
    }
}


@Composable
fun BottomNavItem(
    bottomScreen: BottomScreen,
    navController: NavHostController
) {
    //val isSelected = currentDestination?.hierarchy?.any { it.route == bottomScreen.route } == true

    Box(
        modifier = Modifier
            .clickable(onClick = {
                navController.navigate(bottomScreen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = bottomScreen.icon,
                contentDescription = bottomScreen.title,
                tint = MaterialTheme.colors.onPrimary
            )
            Text(text = bottomScreen.title, color = MaterialTheme.colors.onPrimary)
        }

    }
}