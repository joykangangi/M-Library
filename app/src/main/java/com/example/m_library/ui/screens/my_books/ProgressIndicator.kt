package com.example.m_library.ui.screens.my_books

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m_library.ui.theme.MLibraryTheme


/**
 * startAngle Defns:
 * -90 -> North
 * 0 -> East
 * 90 -> South
 * 180 -> West
 */

@Composable
fun ProgressIndicator(
    modifier: Modifier,
    readChapters: Int,
    totChapters: Int,
    fontSize: TextUnit = 14.sp,
    radius: Dp = 25.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 3.dp,
    animationDuration: Int = 1000, //ms,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val readPercentage = (readChapters.toFloat()/totChapters.toFloat())

    val readPerAnim = animateFloatAsState(
        targetValue = if (animationPlayed) readPercentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animDelay
        )
    )

    //Start animation when activity is launched, key=true ==key never changes
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radius * 2.5f)
    ) {
        Canvas(modifier = modifier.size(radius * 2f)) {

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = readPerAnim.value * 360,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (readPerAnim.value * 100).toInt().toString() + "%",
            fontSize = fontSize
        )
    }
}


@Preview
@Composable
fun ProgressPreview() {
    MLibraryTheme {
        ProgressIndicator(readChapters = 80, totChapters = 100 , modifier = Modifier)
    }
}