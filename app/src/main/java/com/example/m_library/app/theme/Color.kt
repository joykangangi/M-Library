package com.example.m_library.app.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val lightColorScheme = lightColorScheme(
    primary = Color(0xFF1212BD),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF000965),
    secondary = Color(0xFFEB287C),
    onSecondary = Color(0xFFFFFFFF),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF000000),
)

internal val darkColorScheme = darkColorScheme(
    primary = Color(0xFFBDC2FF),
    onPrimary = Color(0xFF1212BD),
    primaryContainer = Color(0xFF000965),
    secondary = Color(0xFFFFb1c8),
    onSecondary = Color(0xFF650033),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    background = Color(0xFF000000),
    onBackground = Color(0xFFE4E1E6),
    surface = Color(0xFF000000),
    onSurface = Color(0xFFE4E1E6),
)
