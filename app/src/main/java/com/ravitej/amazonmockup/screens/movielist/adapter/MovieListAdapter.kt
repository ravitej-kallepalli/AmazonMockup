package com.ravitej.amazonmockup.screens.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravitej.amazonmockup.R
import com.ravitej.amazonmockup.databinding.LayoutMovieItemBinding
import com.ravitej.amazonmockup.model.Movie
import com.ravitej.amazonmockup.network.IMAGE_URL

class MovieListAdapter(
    private val list: List<Movie> = arrayListOf()
) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding: LayoutMovieItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_movie_item,
            parent,
            false
        )

        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class MovieListViewHolder(
    val binding: LayoutMovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie) {

        binding.movieReleaseDate.text = item.releaseDate
        binding.movieTitle.text = item.title

        Glide
            .with(binding.root.context)
            .load(IMAGE_URL + item.posterPath)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.movieImageView);
    }
}
