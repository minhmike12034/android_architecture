package com.example.movieapp.fragment.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.fragment.moviedetail.state.MovieState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonRefresh.setOnClickListener {
            viewModel.getMovie()
        }

        observeFlow()
    }

    private fun observeFlow() {
        viewModel.movieState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state -> updateMovieState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun updateMovieState(state: MovieState) {
        binding.initialLoading.isVisible = state is MovieState.Loading
        binding.buttonRefresh.isVisible = state is MovieState.GetMovieDetailFailure

        when (state) {
            is MovieState.GetMovieDetailSuccess -> {
                val movie = state.movieEntity
                Glide
                    .with(requireContext())
                    .load(movie.imageUrl)
                    .centerCrop()
                    .into(binding.imageMovie)

                binding.textTitle.text = getString(R.string.movie_title, movie.title)
                binding.textOverview.text = getString(R.string.movie_overview, movie.overview)
                binding.textStatus.text = getString(R.string.movie_status, movie.status)
            }

            else -> Unit
        }
    }
}
