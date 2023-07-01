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
import androidx.compose.material3.Text
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
        
        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Headline large",
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Headline medium",
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Headline small",
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Title large",
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Title medium",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Title small",
            style = MaterialTheme.typography.titleSmall,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Body large",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Body medium",
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Body small",
            style = MaterialTheme.typography.bodySmall,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Label large",
            style = MaterialTheme.typography.labelLarge,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Label medium",
            style = MaterialTheme.typography.labelMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Label small",
            style = MaterialTheme.typography.labelSmall,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Display large",
            style = MaterialTheme.typography.displayLarge,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Display medium",
            style = MaterialTheme.typography.displayMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Display small",
            style = MaterialTheme.typography.displaySmall,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_16))
    }
}
