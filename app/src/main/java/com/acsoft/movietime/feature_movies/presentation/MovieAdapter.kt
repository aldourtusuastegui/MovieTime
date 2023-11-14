package com.acsoft.movietime.feature_movies.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acsoft.movietime.R
import com.acsoft.movietime.core.BaseViewHolder
import com.acsoft.movietime.databinding.MovieItemBinding
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.utils.AppConstants
import com.bumptech.glide.Glide

class MovieAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var moviesList = listOf<Movie>()

    fun setMovieList(movieList: List<Movie>) {
        this.moviesList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is MovieViewHolder -> {
                holder.bind(moviesList[position])
            }
        }
    }

    private inner class MovieViewHolder(val binding : MovieItemBinding, val context: Context) :
        BaseViewHolder<Movie>(binding.root) {
        override fun bind(item: Movie) {
            Glide.with(context)
                .load(AppConstants.IMAGE_URL.plus(item.posterPath))
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imgMovie)
        }
    }
}