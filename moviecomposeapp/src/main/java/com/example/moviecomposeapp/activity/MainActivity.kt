package com.example.moviecomposeapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.design.theme.MovieAppTheme
import com.example.moviecomposeapp.navigation.MainNavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = remember {
                mutableStateOf(false)
            }
            MovieAppTheme(darkTheme = isDarkTheme.value) {
                val navController = rememberNavController()
                MainNavigationGraph(
                    navController = navController,
                    isDarkTheme = isDarkTheme.value,
                    onChangeTheme = {
                        val oldValue = isDarkTheme.value
                        isDarkTheme.value = !oldValue
                    },
                )
            }
        }
    }
}
