package com.ravitej.amazonmockup.screens.movielist

import androidx.lifecycle.LiveData
import com.ravitej.amazonmockup.model.MoviesByCategory

/**
 * Contract for MovieListViewModel
 */
interface MovieListViewModel {

    /**
     * Provides a observable stream of movies by categories
     */
    fun getMoviesByCategories(): LiveData<List<MoviesByCategory>>
}