package com.example.moviecomposeapp.screen.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design.dimension.Dimension
import com.example.domain.entity.MovieEntity
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.screen.ErrorItem
import com.example.moviecomposeapp.screen.LoadingItem
import com.example.moviecomposeapp.screen.moviedetail.state.MovieState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailScreen(
    movieState: MovieState,
    onRefreshClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.movie_detail_compose),
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        content = { paddingValues ->
            MovieDetailContent(
                paddingValues = paddingValues,
                movieDetailState = movieState,
                onRefresh = {
                    onRefreshClick.invoke()
                },
            )
        },
    )
}

@Composable
private fun MovieDetailContent(
    paddingValues: PaddingValues,
    movieDetailState: MovieState,
    onRefresh: () -> Unit,
) {
    when (movieDetailState) {
        is MovieState.Loading -> LoadingItem(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        )

        is MovieState.GetMovieDetailFailure -> ErrorItem(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            onRefresh = {
                onRefresh.invoke()
            },
        )

        is MovieState.GetMovieDetailSuccess -> {
            MovieDetailSuccess(
                modifier = Modifier.padding(paddingValues),
                movie = movieDetailState.movieEntity,
            )
        }
    }
}

@Composable
fun MovieDetailSuccess(
    modifier: Modifier,
    movie: MovieEntity,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1F / 1F),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_8))

        Text(
            modifier = Modifier.padding(
                start = Dimension.Spacing_16,
                end = Dimension.Spacing_16,
            ),
            text = stringResource(
                id = R.string.movie_title,
                movie.title.orEmpty(),
            ),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_8))

        Text(
            modifier = Modifier.padding(
                start = Dimension.Spacing_16,
                end = Dimension.Spacing_16,
            ),
            text = stringResource(
                id = R.string.movie_overview,
                movie.overview.orEmpty(),
            ),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(Dimension.Spacing_8))

        Text(
            modifier = Modifier.padding(
                start = Dimension.Spacing_16,
                end = Dimension.Spacing_16,
            ),
            text = stringResource(
                id = R.string.movie_status,
                movie.status.orEmpty(),
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
