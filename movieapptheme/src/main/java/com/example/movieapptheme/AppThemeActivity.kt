@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieapptheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.design.theme.MovieAppTheme

class AppThemeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppThemeContent()
        }
    }
}

@Composable
fun AppThemeContent() {
    val isDarkTheme = remember {
        mutableStateOf(false)
    }
    MovieAppTheme(darkTheme = isDarkTheme.value) {
        MovieAppContent(isDarkTheme = isDarkTheme.value) {
            val oldValue = isDarkTheme.value
            isDarkTheme.value = !oldValue
        }
    }
}

@Composable
fun MovieAppContent(
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    Scaffold(
        topBar = {
            MovieTopAppBar(
                isDarkTheme = isDarkTheme,
                onChangeTheme = onChangeTheme,
            )
        },
        content = { paddingValues ->
            ListMovieButton(Modifier.padding(paddingValues))
        },
    )
}

@Composable
private fun MovieTopAppBar(
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Movie app theme",
                color = Color.White,
            )
        },
        actions = {
            IconButton(onClick = {
                onChangeTheme()
            }) {
                val modeIcon = when (isDarkTheme) {
                    true -> Icons.Default.LightMode
                    else -> Icons.Default.DarkMode
                }
                Icon(
                    modeIcon,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
    )
}
