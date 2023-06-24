package com.example.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.design.dimension.MovieDimension

@Composable
private fun MovieButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = Color.Unspecified,
    endIcon: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier.height(MovieDimension.ButtonHeight_48),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = MovieDimension.Spacing_16,
                vertical = MovieDimension.Spacing_8,
            ),
        ) {
            if (!text.isNullOrEmpty()) {
                Text(
                    text = text,
                    style = textStyle,
                    color = textColor,
                )
            }

            if (endIcon != null) {
                Spacer(modifier = Modifier.width(MovieDimension.Spacing_4))
                endIcon()
            }
        }
    }
}

@Composable
fun MovieSolidButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    endIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    MovieButton(
        modifier = modifier
            .clip(shape = RoundedCornerShape(MovieDimension.Radius_24))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick.invoke() },
        text = text,
        textStyle = textStyle,
        textColor = Color.White,
        endIcon = endIcon,
    )
}

@Composable
fun MovieOutlinedButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    endIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    MovieButton(
        modifier = modifier
            .border(
                width = MovieDimension.BorderWidth_2,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(MovieDimension.Radius_24),
            )
            .clickable { onClick.invoke() },
        text = text,
        textStyle = textStyle,
        endIcon = endIcon,
    )
}

@Composable
fun MovieTextButton(
    modifier: Modifier,
    text: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    textColor: Color = Color.Unspecified,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        if (text != null) {
            Text(
                text = text,
                style = textStyle,
                color = textColor,
            )
        }
    }
}
