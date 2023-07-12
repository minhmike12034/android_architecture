package com.example.moviecomposeapp.screen.listmovie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design.component.MovieOutlinedButton
import com.example.design.dimension.Dimension
import com.example.domain.entity.MovieEntity
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.constant.CONTENT_DESCRIPTION_DARK_MODE
import com.example.moviecomposeapp.constant.CONTENT_DESCRIPTION_LIGHT_MODE
import com.example.moviecomposeapp.constant.TEST_TAG_LAZY_COLUMN_MOVIE
import com.example.moviecomposeapp.screen.ErrorItem
import com.example.moviecomposeapp.screen.LoadingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListMovieScreen(
    listPopularMovies: LazyPagingItems<MovieEntity>,
    onMovieItemClicked: (movieId: String) -> Unit,
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            MovieTopAppBar(
                isDarkTheme = isDarkTheme,
                onChangeTheme = onChangeTheme,
            )
        },
        content = { paddingValues ->
            ListMovieContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                movies = listPopularMovies,
                onMovieItemClicked = onMovieItemClicked,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieTopAppBar(
    isDarkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.list_movie_compose),
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
                val contentDescription = when (isDarkTheme) {
                    true -> CONTENT_DESCRIPTION_DARK_MODE
                    else -> CONTENT_DESCRIPTION_LIGHT_MODE
                }
                Icon(
                    modeIcon,
                    contentDescription = contentDescription,
                    tint = Color.White,
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    )
}

@Composable
fun ListMovieContent(
    modifier: Modifier,
    movies: LazyPagingItems<MovieEntity>,
    onMovieItemClicked: (movieId: String) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    when (movies.loadState.refresh) {
        is LoadState.Loading -> LoadingItem(Modifier.fillMaxSize())
        is LoadState.Error -> ErrorItem(
            Modifier.fillMaxSize(),
            onRefresh = {
                movies.refresh()
            },
        )

        else -> LazyColumn(
            modifier = modifier.padding(
                top = Dimension.Spacing_12,
                bottom = Dimension.Spacing_12,
                start = Dimension.Spacing_20,
                end = Dimension.Spacing_20,
            ).testTag(TEST_TAG_LAZY_COLUMN_MOVIE),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(Dimension.Spacing_12),
        ) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.id },
            ) { index ->
                movies[index]?.let {
                    MovieItem(
                        modifier = Modifier
                            .testTag(it.id.toString())
                            .fillMaxWidth()
                            .aspectRatio(3.5F / 2F),
                        movieEntity = it,
                        onMovieItemClicked = onMovieItemClicked,
                    )
                }
            }

            when (movies.loadState.append) {
                is LoadState.Error -> item {
                    LoadMoreErrorItem(
                        modifier = Modifier.fillMaxWidth(),
                        onRetry = {
                            movies.retry()
                        },
                    )
                }

                is LoadState.Loading -> item { LoadingItem(modifier = Modifier.fillMaxWidth()) }
                else -> Unit
            }
        }
    }
}

@Composable
private fun MovieItem(
    modifier: Modifier,
    movieEntity: MovieEntity,
    onMovieItemClicked: (movieId: String) -> Unit,
) {
    Card(
        modifier = modifier
            .clickable {
                onMovieItemClicked.invoke(
                    movieEntity.id.toString(),
                )
            },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = Dimension.Elevation_8,
        ),
        shape = RoundedCornerShape(Dimension.Radius_8),
    ) {
        Box(modifier = Modifier) {
            AsyncImage(
                modifier = Modifier,
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieEntity.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5F))
                    .align(Alignment.BottomCenter),
                textAlign = TextAlign.Center,
                text = movieEntity.title.orEmpty(),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun LoadMoreErrorItem(
    modifier: Modifier,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        MovieOutlinedButton(modifier = Modifier, text = stringResource(R.string.retry)) {
            onRetry.invoke()
        }
    }
}
