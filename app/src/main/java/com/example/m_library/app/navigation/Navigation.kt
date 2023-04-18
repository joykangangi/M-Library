package com.example.m_library.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.m_library.NavGraphs
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberAnimatedNavController()

    Scaffold(
        modifier = modifier,
        content = { scaffoldPadding ->
            AppNavigation(
                modifier = Modifier.padding(scaffoldPadding),
                navHostController = navController,
            )
        },
        bottomBar = {
            BottomAppBar(
                navController = navController,
            )

        },
    )
}

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun AppNavigation(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navHostEngine: NavHostEngine = rememberAnimatedNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations()
    )

    DestinationsNavHost(
        modifier = modifier,
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController,
    )
}