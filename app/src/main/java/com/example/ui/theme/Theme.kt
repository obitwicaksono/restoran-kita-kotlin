package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryOrangeDark,
    onPrimary = OnPrimaryOrangeDark,
    primaryContainer = PrimaryContainerOrangeDark,
    onPrimaryContainer = OnPrimaryContainerOrangeDark,
    secondary = SecondaryTealDark,
    onSecondary = OnSecondaryTealDark,
    secondaryContainer = SecondaryContainerTealDark,
    onSecondaryContainer = OnSecondaryContainerTealDark,
    tertiary = TertiaryAmberDark,
    onTertiary = OnTertiaryAmberDark,
    tertiaryContainer = TertiaryContainerAmberDark,
    onTertiaryContainer = OnTertiaryContainerAmberDark,
    background = BackgroundCoffee,
    onBackground = OnBackgroundCoffee,
    surface = SurfaceCoffee,
    onSurface = OnSurfaceCoffee,
    surfaceVariant = SurfaceVariantCoffee,
    onSurfaceVariant = OnSurfaceVariantCoffee,
    surfaceContainerLow = SurfaceContainerLowCoffee,
    surfaceContainer = SurfaceContainerCoffee,
    surfaceContainerHigh = SurfaceContainerHighCoffee,
    surfaceContainerHighest = SurfaceContainerHighestCoffee,
    outline = OutlineCoffee,
    outlineVariant = OutlineVariantCoffee
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryOrange,
    onPrimary = OnPrimaryOrange,
    primaryContainer = PrimaryContainerOrange,
    onPrimaryContainer = OnPrimaryContainerOrange,
    secondary = SecondaryTeal,
    onSecondary = OnSecondaryTeal,
    secondaryContainer = SecondaryContainerTeal,
    onSecondaryContainer = OnSecondaryContainerTeal,
    tertiary = TertiaryAmber,
    onTertiary = OnTertiaryAmber,
    tertiaryContainer = TertiaryContainerAmber,
    onTertiaryContainer = OnTertiaryContainerAmber,
    background = BackgroundPeach,
    onBackground = OnBackgroundPeach,
    surface = SurfacePeach,
    onSurface = OnSurfacePeach,
    surfaceVariant = SurfaceVariantPeach,
    onSurfaceVariant = OnSurfaceVariantPeach,
    surfaceContainerLow = SurfaceContainerLowPeach,
    surfaceContainer = SurfaceContainerPeach,
    surfaceContainerHigh = SurfaceContainerHighPeach,
    surfaceContainerHighest = SurfaceContainerHighestPeach,
    outline = OutlinePeach,
    outlineVariant = OutlineVariantPeach
)

@Composable
fun RestoranTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
