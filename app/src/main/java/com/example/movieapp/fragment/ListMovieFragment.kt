package com.example.movieapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.domain.entity.MovieEntity
import com.example.movieapp.databinding.FragmentListMovieBinding
import com.example.movieapp.fragment.adapter.LoadMoreAdapter
import com.example.movieapp.fragment.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListMovieFragment : Fragment(), MovieAdapter.MovieListener {

    private lateinit var binding: FragmentListMovieBinding

    private val viewModel: ListMovieViewModel by viewModels()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeFlow()
    }

    private fun observeFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.initialLoading.isVisible = loadStates.refresh is LoadState.Loading
                binding.buttonRefresh.isVisible = loadStates.refresh is LoadState.Error
            }
        }

        viewModel.listPopularMovies
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { movieAdapter.submitData(it) }
            .launchIn(lifecycleScope)
    }

    private fun setupView() {
        binding.buttonRefresh.setOnClickListener {
            movieAdapter.refresh()
        }
        movieAdapter = MovieAdapter(this)
        binding.rcvMovie.apply {
            adapter = movieAdapter.withLoadStateFooter(
                LoadMoreAdapter {
                    movieAdapter.retry()
                },
            )
        }
    }

    override fun onMovieClick(movie: MovieEntity) {
    }
}
