package com.example.design.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class MovieAppColorsPalette(
    val buttonRed: Color = Color.Unspecified,
)

val LocalMovieAppColorsPalette = staticCompositionLocalOf { MovieAppColorsPalette() }
