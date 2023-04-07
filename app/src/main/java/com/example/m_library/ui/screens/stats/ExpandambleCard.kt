package com.example.m_library.ui.screens.stats

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
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


//@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    card: ExpandableCardModel,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(
        targetState = expanded,
        label = null
    )

    val arrowRotation: Float by transition.animateFloat(
        transitionSpec = { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "arrowTransition",
        targetValueByState = { expanded->
            if (expanded) 180f else 0f
        }
    )

    val cardBgColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "cardColorTransition",
        targetValueByState = { expanded->
            if (expanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        }
    )

    val contentColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "contentColorTransition",
        targetValueByState = { expanded->
            if (expanded) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
        }
    )

    val cardModifiers = modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        .clickable { onCardArrowClick() }
        .padding(horizontal = 14.dp, vertical = 8.dp)

    Card(
        modifier = cardModifiers,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = cardBgColor,
        contentColor = contentColor,
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier.padding(12.dp).fillMaxWidth()
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardTitle(title = card.title)
                Spacer(modifier = modifier.width(10.dp))
                CardNumber(number = card.number)
                Spacer(modifier = modifier.weight(1f))
                CardArrow(cardArrowClick = onCardArrowClick, arrowDegrees = arrowRotation)
            }

            if (expanded) {
                BookNames(books = card.body)
            }
        }
    }
}