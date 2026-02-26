package com.example.dragonballapp_cikicmilos.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Orange500,
    onPrimary = TextOnLight,
    primaryContainer = Orange700,
    onPrimaryContainer = TextOnDark,
    secondary = Blue500,
    onSecondary = TextOnDark,
    background = DarkBackground,
    onBackground = TextOnDark,
    surface = DarkSurface,
    onSurface = TextOnDark,
    surfaceVariant = DarkCard,
    onSurfaceVariant = TextOnDark,
    error = Red500
)

private val LightColorScheme = lightColorScheme(
    primary = Orange500,
    onPrimary = LightSurface,
    primaryContainer = Orange200,
    onPrimaryContainer = TextOnLight,
    secondary = Blue500,
    onSecondary = LightSurface,
    background = LightBackground,
    onBackground = TextOnLight,
    surface = LightSurface,
    onSurface = TextOnLight,
    surfaceVariant = Orange200,
    onSurfaceVariant = TextOnLight,
    error = Red500
)

@Composable
fun DragonBallTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
