package com.example.movieapptheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.design.component.MovieOutlinedButton
import com.example.design.component.MovieSolidButton
import com.example.design.component.MovieTextButton
import com.example.design.dimension.Dimension

@Composable
fun ListMovieButton(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = Dimension.Spacing_16,
                end = Dimension.Spacing_16,
            )
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        MovieSolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Solid button",
        ) {
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        MovieSolidButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Solid button ",
            endIcon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                )
            },
        ) {
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        MovieOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Outlined Button",
        ) {
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        MovieOutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Outlined Button",
            endIcon = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.inverseSurface,
                )
            },
        ) {
        }

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        MovieTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Text Button",
        ) {
        }
    }
}