package io.github.emusute1212.makasetechoice.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Pink,
    secondary = Gray200,
    tertiary = Yellow,
    surface = White,
    onSurface = Black100,
    background = White,
    outline = Gray100,
    outlineVariant = Gray50,
)

private val DarkColorScheme = darkColorScheme(
    primary = Pink,
    secondary = Gray200,
    tertiary = Yellow,
    surface = Black100,
    onSurface = White,
    background = Black050,
    outline = Gray50,
    outlineVariant = Gray100,
)

@Composable
fun MakaseteChoiceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Material color is unused
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}