package com.example.movieapp.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.MovieEntity
import com.example.movieapp.databinding.ItemMovieBinding

class MovieAdapter(
    private val listener: MovieListener,
) : PagingDataAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return MovieViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val listener: MovieListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                Glide
                    .with(root.context)
                    .load(movie.imageUrl)
                    .centerCrop()
                    .into(binding.imageMovie)
                textMovieName.text = movie.title
                root.setOnClickListener {
                    listener.onMovieClick(movie)
                }
            }
        }
    }

    class MovieDiffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }

    interface MovieListener {
        fun onMovieClick(movie: MovieEntity)
    }
}
