package com.example.moviecomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviecomposeapp.navigation.NavRoute.MovieDetail.movieId
import com.example.moviecomposeapp.route.ListMovieRoute
import com.example.moviecomposeapp.route.LoginRoute
import com.example.moviecomposeapp.route.MovieDetailRoute

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.ListMovie.path,
        modifier = modifier,
    ) {
        composable(route = NavRoute.ListMovie.path) {
            ListMovieRoute(
                onMovieItemClicked = { movieId ->
                    navController.navigate(
                        NavRoute.MovieDetail.withArgs(
                            movieId,
                        ),
                    )
                },
                isDarkTheme = isDarkTheme,
                onChangeTheme = onChangeTheme,
                onLoginClick = {
                    navController.navigate(
                        NavRoute.Login.path,
                    )
                },
            )
        }

        composable(
            route = NavRoute.MovieDetail.withArgsFormat(
                movieId,
            ),
            arguments = listOf(
                navArgument(movieId) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            ),
        ) {
            MovieDetailRoute(onBackClick = {
                navController.popBackStack()
            })
        }

        composable(route = NavRoute.Login.path) {
            LoginRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
