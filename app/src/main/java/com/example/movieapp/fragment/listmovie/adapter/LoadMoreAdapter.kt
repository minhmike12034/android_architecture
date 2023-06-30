package com.example.movieapp.fragment.listmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemLoadMoreBinding

class LoadMoreAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {

    private lateinit var binding: ItemLoadMoreBinding

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }

    class ViewHolder(
        private val binding: ItemLoadMoreBinding,
        private val retry: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(state: LoadState) {
            binding.apply {
                progressCircular.isVisible = state is LoadState.Loading
                buttonRetry.isVisible = state is LoadState.Error
                binding.buttonRetry.setOnClickListener { retry() }
            }
        }
    }
}
