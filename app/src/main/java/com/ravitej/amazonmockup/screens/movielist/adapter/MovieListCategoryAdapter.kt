package com.ravitej.amazonmockup.screens.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ravitej.amazonmockup.R
import com.ravitej.amazonmockup.databinding.LayoutMovieListItemBinding
import com.ravitej.amazonmockup.model.MoviesByCategory

class MovieListCategoryAdapter :
    ListAdapter<MoviesByCategory, MovieListByCategoryViewHolder>(MoviesByCategoryDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListByCategoryViewHolder {
        val binding: LayoutMovieListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_movie_list_item,
            parent,
            false
        )

        return MovieListByCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListByCategoryViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.binding.executePendingBindings()
    }

    override fun submitList(list: List<MoviesByCategory>?) {
        super.submitList(list?.let { ArrayList(list) })
    }
}

class MovieListByCategoryViewHolder(
    val binding: LayoutMovieListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MoviesByCategory) {
        binding.categoryName.text = item.categoryName
        binding.movieListRecyclerView.adapter = MovieListAdapter(item.movies)
    }
}

object MoviesByCategoryDiff : DiffUtil.ItemCallback<MoviesByCategory>() {
    override fun areItemsTheSame(
        oldItem: MoviesByCategory,
        newItem: MoviesByCategory
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MoviesByCategory,
        newItem: MoviesByCategory
    ): Boolean {
        return oldItem.categoryName == newItem.categoryName
    }
}