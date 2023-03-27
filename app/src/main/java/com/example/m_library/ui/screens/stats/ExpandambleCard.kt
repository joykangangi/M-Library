package com.example.m_library.ui.screens.stats

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m_library.model.ExpandableCardModel
import com.example.m_library.ui.screens.stats.Constants.EXPAND_ANIMATION_DURATION


@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    card: ExpandableCardModel,
    onCardArrowClick: () -> Unit,
    expanded: Boolean
) {

    /*val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")

    val cardBgColor by transition.animateColor(
        { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "bgColorTransition"
    ) {
        Log.i("Expandamble Card", "$expanded")
        if (expanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    }

    val contentColor by transition.animateColor(
        { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "textColorTransition"
    ) {
        if (expanded) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
    }


    val cardRoundedCorners by transition.animateDp(
        { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "cornersTransition"
    ) {
        if (expanded) 0.dp else 16.dp
    }

    //Todo Here i should consider a long list
    val cardPaddingHorizontal by transition.animateDp(
        { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "paddingTransition"
    ) {
        if (expanded) 48.dp else 24.dp
    }

    val cardElevation by transition.animateDp(
        { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "elevationTransition"
    ) {
        if (expanded) 24.dp else 4.dp
    }

    val arrowRotation by transition.animateFloat(
        { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "arrowTransition"
    ) {
        if (expanded) 0f else 180f
    }*/


    val arrowRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    )

    val cardBgColor by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    )

    val cardRoundedCorners by animateDpAsState(
        targetValue = if (expanded) 0.dp else 16.dp,
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    )

    val contentColor by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface,
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    )

    val cardElevation by animateDpAsState(
        targetValue = if (expanded) 24.dp else 4.dp,
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        // .padding(horizontal = cardPaddingHorizontal, vertical = 8.dp),
        shape = RoundedCornerShape(cardRoundedCorners),
        backgroundColor = cardBgColor,
        contentColor = contentColor,
        elevation = cardElevation
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardTitle(title = card.title)
                CardNumber(number = card.number)
                Spacer(modifier = Modifier.width(10.dp))
                CardArrow(cardArrowClick = onCardArrowClick, arrowDegrees = arrowRotation)
            }

            if (expanded) {
                BookNames(books = card.body)
            }
        }

    }
}