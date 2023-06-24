package com.example.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.design.dimension.Dimension

@Composable
fun MovieSolidButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    endIcon: @Composable () -> Unit = {},
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(Dimension.Button_Height_48),
        onClick = { onClick.invoke() },
    ) {
        if (text != null) {
            Text(
                text = text,
                color = Color.White,
            )
        }
        endIcon()
    }
}

@Composable
fun MovieOutlinedButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    endIcon: @Composable () -> Unit = {},
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier
            .height(Dimension.Button_Height_48),
        border = BorderStroke(Dimension.BorderWidth_1, MaterialTheme.colorScheme.primary),
        onClick = { onClick.invoke() },
    ) {
        if (text != null) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.inverseSurface,
            )
        }
        endIcon()
    }
}

@Composable
fun MovieTextButton(
    modifier: Modifier,
    text: String? = null,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier.height(Dimension.Button_Height_48),
        onClick = onClick,
    ) {
        if (text != null) {
            Text(
                text = text,
            )
        }
    }
}
