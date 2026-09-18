package com.pocketbudget.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PocketBudgetColorScheme = lightColorScheme(
    primary = Violet600,
    background = Slate100,
    surface = White,
    onPrimary = White,
    onBackground = Slate900,
    onSurface = Slate900
)

@Composable
fun PocketBudgetMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PocketBudgetColorScheme,
        typography = Typography,
        content = content
    )
}