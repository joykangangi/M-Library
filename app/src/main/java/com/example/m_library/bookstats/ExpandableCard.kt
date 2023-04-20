package com.example.m_library.bookstats

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.m_library.R
import com.example.m_library.app.data.local.Book
import com.example.m_library.app.theme.MLibraryTheme
import com.example.m_library.app.theme.mediumPadding
import com.example.m_library.app.theme.secondaryPadding
import com.example.m_library.app.theme.smallPadding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val EXPAND_ANIMATION_DURATION = 500

@Composable
fun ExpandableCard(
    title: String,
    books: ImmutableList<Book>,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    val onArrowClick = remember {
        {
            expanded = !expanded
        }
    }

    val transition = updateTransition(
        targetState = expanded,
        label = null
    )

    val arrowRotation: Float by transition.animateFloat(
        transitionSpec = { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "arrowTransition",
        targetValueByState = { if (it) 180f else 0f }
    )

    val cardBgColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "cardColorTransition",
        targetValueByState = {
            if (it) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        }
    )

    val contentColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = EXPAND_ANIMATION_DURATION) },
        label = "contentColorTransition",
        targetValueByState = {
            if (it) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
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
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onArrowClick,
        )
        .padding(horizontal = mediumPadding(), vertical = smallPadding())

    Card(
        modifier = cardModifiers,
        shape = RoundedCornerShape(secondaryPadding()),
        colors = CardDefaults.cardColors(
            containerColor = cardBgColor,
            contentColor = contentColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = smallPadding() / 2,
        ),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumPadding()),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            Spacer(modifier = Modifier.width(mediumPadding()))
                            Text(
                                modifier = Modifier
                                    .background(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                    .padding(horizontal = smallPadding() / 2),
                                text = books.size.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = modifier.weight(1f))
                            IconButton(
                                onClick = onArrowClick,
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = stringResource(id = R.string.expand_icon),
                                        modifier = Modifier.rotate(arrowRotation)
                                    )
                                }
                            )
                        }
                    )
                    if (expanded) {
                        books.forEachIndexed { index, book ->
                            Text(text = book.title)
                            if (index < books.lastIndex) Divider(
                                modifier = Modifier.padding(vertical = smallPadding() / 2)
                            )
                        }
                    }
                },
            )
        },
    )
}

@Preview
@Composable
private fun ExpandedCardPreview() = MLibraryTheme {
    ExpandableCard(title = "Expanded Card", books = persistentListOf())
}