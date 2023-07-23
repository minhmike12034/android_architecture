package com.example.design.theme

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.design.color.CustomColorsPalette
import com.example.design.color.LocalCustomColorsPalette

private val DarkColorScheme = darkColorScheme(
    primary = LightBlue300,
//    onPrimary = ...
//    secondary = ...
)

private val LightColorScheme = lightColorScheme(
    primary = LightBlue500,
//    onPrimary = ...
//    secondary = ...
)

// Custom outside material theme
private val OnLightCustomColorsPalette = CustomColorsPalette(
    customButtonRed = LightRed300,
)

private val OnDarkCustomColorsPalette = CustomColorsPalette(
    customButtonRed = DarkRed700,
)

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    val customColorsPalette =
        if (darkTheme) {
            OnDarkCustomColorsPalette
        } else {
            OnLightCustomColorsPalette
        }
    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}
