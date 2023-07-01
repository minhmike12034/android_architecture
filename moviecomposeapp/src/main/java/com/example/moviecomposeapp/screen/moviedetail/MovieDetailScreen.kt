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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.design.dimension.Dimension
import com.example.domain.entity.MovieDetailEntity
import com.example.moviecomposeapp.R
import com.example.moviecomposeapp.screen.ErrorItem
import com.example.moviecomposeapp.screen.LoadingItem
import com.example.moviecomposeapp.screen.moviedetail.state.MovieDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieDetailScreen(
    viewModel: MovieDetailViewModel,
    onBackClick: () -> Unit,
) {
    val movieDetailState = viewModel.movieDetailState.collectAsState()
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
                movieDetailState = movieDetailState.value,
                onRefresh = {
                    viewModel.getMovieDetail()
                },
            )
        },
    )
}

@Composable
private fun MovieDetailContent(
    paddingValues: PaddingValues,
    movieDetailState: MovieDetailState,
    onRefresh: () -> Unit,
) {
    when (movieDetailState) {
        is MovieDetailState.Loading -> LoadingItem(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        )

        is MovieDetailState.GetMovieDetailFailure -> ErrorItem(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            onRefresh = {
                onRefresh.invoke()
            },
        )

        is MovieDetailState.GetMovieDetailSuccess -> {
            MovieDetailSuccess(
                modifier = Modifier.padding(paddingValues),
                movieDetailEntity = movieDetailState.movieDetailEntity,
            )
        }
    }
}

@Composable
fun MovieDetailSuccess(modifier: Modifier, movieDetailEntity: MovieDetailEntity) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1F / 1F),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetailEntity.imageUrl)
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
                movieDetailEntity.title.orEmpty(),
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
                movieDetailEntity.overview.orEmpty(),
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
                movieDetailEntity.status.orEmpty(),
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
