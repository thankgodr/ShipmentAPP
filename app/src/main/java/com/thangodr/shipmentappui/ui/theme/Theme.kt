package com.thangodr.shipmentappui.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColors(
    primary = Color(0xFF8F7ADB), // Darker purple for primary elements
    onPrimary = Color.White, // White text on primary elements
    primaryVariant = Color(0xFF3E2E7A), // Dark purple background for primary containers

    secondary = Color(0xFFF6A833), // Bright orange for secondary elements
    onSecondary = Color.Black, // Dark text on secondary elements
    secondaryVariant = Color(0xFF4F3A0B), // Dark orange background for secondary containers

    background = Color(0xFF121212), // Dark grey/black background color for the screen
    onBackground = Color(0xFFE0E0E0), // Light grey text color for general content

    surface = Color(0xFF1E1E1E), // Dark grey for surface elements
    onSurface = Color(0xFFE0E0E0), // Light grey text color for surface elements

    error = Color(0xFFCF6679), // Red error color for dark theme
    onError = Color.Black // Dark text on error elements
)


private val LightColorScheme = lightColors(
    primary = APP_BLUE,
    onPrimary = Color.White,
    primaryVariant = Color(0xFFEDE7FB), // Light purple/grey background for primary elements

    secondary = APP_ORANGE,
    onSecondary = Color.White,
    secondaryVariant = Color(0xFFFDE9D3), // Light orange background for secondary elements

    background = Color(0xFFFFFFFF), // White background color for the screen
    onBackground = Color(0xFF2C2C2C), // Dark grey/black text color for general content

    surface = Color(0xFFF6F6F6), // Light grey background for surface elements
    onSurface = Color(0xFF2C2C2C), // Dark grey/black text color for surface elements

    error = Color(0xFFB00020), // Red error color
    onError = Color.White, // White text color on error elements
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