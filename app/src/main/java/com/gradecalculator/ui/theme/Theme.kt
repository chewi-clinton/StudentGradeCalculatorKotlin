package com.gradecalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PurpleColorScheme = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = PurpleLight,
    onPrimaryContainer = PurpleDark,
    secondary = PurpleGrey40,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = androidx.compose.ui.graphics.Color(0xFF1C1B1F),
    background = PurpleSurface,
    onBackground = androidx.compose.ui.graphics.Color(0xFF1C1B1F)
)

@Composable
fun GradeCalculatorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PurpleColorScheme,
        typography = Typography,
        content = content
    )
}
