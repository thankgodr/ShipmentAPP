package com.thangodr.shipmentappui.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8F7ADB), // Darker purple for primary elements
    onPrimary = Color.White, // White text on primary elements
    primaryContainer = Color(0xFF3E2E7A), // Dark purple background for primary containers
    onPrimaryContainer = Color(0xFFE1D8FF), // Light text color for primary containers

    secondary = Color(0xFFF6A833), // Bright orange for secondary elements
    onSecondary = Color.Black, // Dark text on secondary elements
    secondaryContainer = Color(0xFF4F3A0B), // Dark orange background for secondary containers
    onSecondaryContainer = Color(0xFFFFE0B2), // Light text color for secondary containers

    tertiary = Color(0xFF48C8FF), // Bright blue for tertiary elements
    onTertiary = Color.Black, // Dark text on tertiary elements
    tertiaryContainer = Color(0xFF003545), // Dark blue background for tertiary containers
    onTertiaryContainer = Color(0xFFB3E7FF), // Light text color for tertiary containers

    background = Color(0xFF121212), // Dark grey/black background color for the screen
    onBackground = Color(0xFFE0E0E0), // Light grey text color for general content

    surface = Color(0xFF1E1E1E), // Dark grey for surface elements
    onSurface = Color(0xFFE0E0E0), // Light grey text color for surface elements

    error = Color(0xFFCF6679), // Red error color for dark theme
    onError = Color.Black, // Dark text on error elements
    errorContainer = Color(0xFFB3261E), // Dark red background for error elements
    onErrorContainer = Color(0xFFF2B8B5) // Light text color for error container
)


private val LightColorScheme = lightColorScheme(
    primary = APP_BLUE,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEDE7FB), // Light purple/grey background for primary elements
    onPrimaryContainer = Color(0xFF2E295E), // Text color for primary container

    secondary = APP_ORANGE, // Orange color used in the button
    onSecondary = Color.White, // Text color on secondary elements
    secondaryContainer = Color(0xFFFDE9D3), // Light orange background for secondary elements
    onSecondaryContainer = Color(0xFF4A3711), // Text color for secondary container

    tertiary = Color(0xFF00A9E0), // Light blue color used in other buttons/links
    onTertiary = Color.White, // Text color on tertiary elements
    tertiaryContainer = Color(0xFFD2F2FF), // Light blue background for tertiary elements
    onTertiaryContainer = Color(0xFF00293E), // Text color for tertiary container

    background = Color(0xFFFFFFFF), // White background color for the screen
    onBackground = Color(0xFF2C2C2C), // Dark grey/black text color for general content

    surface = Color(0xFFF6F6F6), // Light grey background for surface elements
    onSurface = Color(0xFF2C2C2C), // Dark grey/black text color for surface elements

    error = Color(0xFFB00020), // Red error color
    onError = Color.White, // White text color on error elements
    errorContainer = Color(0xFFF9DEDC), // Light red background for error elements
        onErrorContainer = Color(0xFF410002) // Dark red text color for error container
)


@Composable
fun  ShipmentAPPUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}