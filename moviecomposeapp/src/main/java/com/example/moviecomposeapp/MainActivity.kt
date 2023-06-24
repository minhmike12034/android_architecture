package com.example.moviecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.design.component.MovieOutlinedButton
import com.example.design.component.MovieSolidButton
import com.example.design.component.MovieTextButton
import com.example.design.dimension.MovieDimension
import com.example.design.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@Composable
fun MovieApp() {
    MovieAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier =
                Modifier
                    .padding(start = MovieDimension.Spacing_16, end = MovieDimension.Spacing_16)
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(MovieDimension.Spacing_16))
                MovieSolidButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Solid button",
                ) {
                }

                Spacer(modifier = Modifier.height(MovieDimension.Spacing_16))
                MovieSolidButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Solid button",
                    endIcon = {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    },
                ) {
                }

                Spacer(modifier = Modifier.height(MovieDimension.Spacing_16))
                MovieOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Outlined Button",
                ) {
                }

                Spacer(modifier = Modifier.height(MovieDimension.Spacing_16))
                MovieOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Outlined Button",
                    endIcon = {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = null,
                        )
                    },
                ) {
                }

                Spacer(modifier = Modifier.height(MovieDimension.Spacing_16))
                MovieTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Text Button",
                ) {
                }
            }
        }
    }
}
