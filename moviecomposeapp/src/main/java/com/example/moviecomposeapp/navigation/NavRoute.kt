package com.example.moviecomposeapp.navigation

sealed class NavRoute(val path: String) {

    object ListMovie : NavRoute(path = "list_movie")

    object MovieDetail : NavRoute(path = "movie_detail") {
        const val movieId = "movieId"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}
