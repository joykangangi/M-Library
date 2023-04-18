package com.example.m_library.app.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.m_library.app.theme.MLibraryTheme
import com.example.m_library.app.theme.smallPadding

@Composable
fun BookProgress(
    modifier: Modifier = Modifier,
    readChapters: Int,
    totalChapters: Int,
    textStyle: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyMedium,
    radius: Dp = 25.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = smallPadding() / 2,
    animationDuration: Int = 1000, //ms,
    animationDelay: Int = 0
) {
    val percentage = remember(totalChapters, readChapters) {
        if (totalChapters > 0) {
            readChapters.toFloat() / totalChapters.toFloat()
        } else 0f
    }

    var animationPlayed by remember { mutableStateOf(false) }

    val animation = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        ),
        label = "read_progress"
    )

    // Start animation when composable is launched
    LaunchedEffect(key1 = Unit, block = {
        animationPlayed = true
    })

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radius * 2.5f),
        content = {
            Canvas(
                modifier = Modifier.size(radius * 2f),
                onDraw = {
                    drawArc(
                        color = color,
                        startAngle = -90f,
                        sweepAngle = animation.value * 360,
                        useCenter = false,
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }
            )

            Text(
                text = (animation.value * 100).toInt().toString() + "%",
                style = textStyle,
            )
        }
    )
}


@Preview
@Composable
fun ProgressPreview() {
    MLibraryTheme {
        BookProgress(readChapters = 80, totalChapters = 100, modifier = Modifier)
    }
}