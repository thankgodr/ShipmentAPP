package com.thangodr.shipmentappui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColors(
    primary = Color(0xFF8F7ADB),
    onPrimary = Color.White,
    primaryVariant = Color(0xFF3E2E7A),

    secondary = Color(0xFFF6A833),
    onSecondary = Color.Black,
    secondaryVariant = Color(0xFF4F3A0B),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE0E0E0),

    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),

    error = Color(0xFFCF6679),
    onError = Color.Black
)


private val LightColorScheme = lightColors(
    primary = APP_BLUE,
    onPrimary = Color.White,
    primaryVariant = Color(0xFFEDE7FB),

    secondary = APP_ORANGE,
    onSecondary = Color.White,
    secondaryVariant = Color(0xFFFDE9D3),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF2C2C2C),

    surface = Color(0xFFF6F6F6),
    onSurface = Color(0xFF2C2C2C),

    error = Color(0xFFB00020),
    onError = Color.White,
)


@Composable
fun  ShipmentAPPUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}