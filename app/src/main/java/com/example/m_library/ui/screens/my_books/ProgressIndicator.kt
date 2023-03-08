package com.example.m_library.ui.screens.my_books

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.m_library.R
import com.example.m_library.ui.theme.MLibraryTheme

@Composable
fun ProgressIndicator(
    size: Dp = 40.dp,
    foregroundIndicatorColor: Color = MaterialTheme.colors.primary,
    shadowColor: Color = MaterialTheme.colors.surface,
    indicatorThickness: Dp = 10.dp,
    chaptersRead: Float,
    animationDuration: Int = 1000,
    chapterTextStyle: TextStyle = TextStyle(
        fontSize = MaterialTheme.typography.h3.fontSize
    ),
    remainingTextStyle: TextStyle = TextStyle(
        fontSize = MaterialTheme.typography.body1.fontSize
    )
) {
    //It remembers the chapterReadValue
    var chaptersReadRemember by remember {
        mutableStateOf(-1f)
    }

    //Animate foreground indicator
    val chaptersReadAnimate = animateFloatAsState(
        targetValue = chaptersReadRemember,
        animationSpec = tween(durationMillis = animationDuration)
    )

    //Start animation when activity is launched
    LaunchedEffect(key1 = Unit) {
        chaptersReadRemember = chaptersRead
    }

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size)) {
            //For Shadow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(shadowColor, Color.White),
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2),
                    radius = this.size.height / 2
                ),
                radius = this.size.height / 2,
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )

            //Convert ChaptersREAD TO angle
            val sweepAngle = (chaptersReadAnimate.value) * 360 / 100

            //Foreground indicator
            drawArc(
                color = foregroundIndicatorColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(),
                    y = (indicatorThickness / 2).toPx()
                )
            )
        }

        //Display REM chapters
        DisplayText(
            animateNumber = chaptersReadAnimate,
            chapterTextStyle = chapterTextStyle,
            remainingTextStyle = remainingTextStyle
        )
    }
}

@Composable
fun DisplayText(
    animateNumber: State<Float>,
    chapterTextStyle: TextStyle,
    remainingTextStyle: TextStyle
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Text that shows the number inside the circle
        Text(text = (animateNumber.value).toInt().toString(), style = chapterTextStyle)

        Spacer(modifier = Modifier.height(2.dp))

        Text(text = stringResource(id = R.string.rem), style = remainingTextStyle)
    }
}


@Preview
@Composable
fun ProgressPreview() {
    MLibraryTheme() {
        ProgressIndicator(chaptersRead = 0.5f)
    }
}