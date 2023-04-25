package com.example.m_library.app.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import com.example.m_library.appDestination
import com.example.m_library.destinations.BookDetailRouteDestination
import com.example.m_library.destinations.BookStatsRouteDestination
import com.example.m_library.destinations.BooksRouteDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

private const val OFFSET = 1000
private val slideEffect = spring<IntOffset>(dampingRatio = Spring.DampingRatioMediumBouncy)
private val popupEffect = tween<IntOffset>(
    durationMillis = 2000,
    easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
)

@OptIn(ExperimentalAnimationApi::class)
object NavigationTransitions : DestinationStyle.Animated {

    private val horizontalDestinations =
        listOf(BooksRouteDestination, BookDetailRouteDestination, BookStatsRouteDestination)

    private fun enterTransition(
        isHorizontal: Boolean,
        isNegative: Boolean,
    ): EnterTransition {
        val offset = if (isNegative) -OFFSET else OFFSET
        val animationSpec = if (isHorizontal) slideEffect else popupEffect

        return if (isHorizontal) {
            slideInHorizontally(initialOffsetX = { offset }, animationSpec = animationSpec)
        } else {
            slideInVertically(initialOffsetY = { offset }, animationSpec = animationSpec)
        }
    }

    private fun exitTransition(
        isHorizontal: Boolean,
        isNegative: Boolean,
    ): ExitTransition {
        val offset = if (isNegative) -OFFSET else OFFSET
        val animationSpec = if (isHorizontal) slideEffect else popupEffect

        return if (isHorizontal) {
            slideOutHorizontally(targetOffsetX = { offset }, animationSpec = animationSpec)
        } else {
            slideOutVertically(targetOffsetY = { offset }, animationSpec = animationSpec)
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return enterTransition(
            isHorizontal = initialState.appDestination() in horizontalDestinations,
            isNegative = false,
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return exitTransition(
            isHorizontal = initialState.appDestination() in horizontalDestinations,
            isNegative = true,
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return enterTransition(
            isHorizontal = initialState.appDestination() in horizontalDestinations,
            isNegative = true,
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return exitTransition(
            isHorizontal = initialState.appDestination() in horizontalDestinations,
            isNegative = false,
        )
    }
}