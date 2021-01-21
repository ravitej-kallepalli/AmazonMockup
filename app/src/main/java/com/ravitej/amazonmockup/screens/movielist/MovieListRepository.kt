package com.ravitej.amazonmockup.screens.movielist

import androidx.lifecycle.LiveData
import com.ravitej.amazonmockup.model.MoviesByCategory

/**
 * Contract for MovieListRepository
 */
interface MovieListRepository {
    /**
     * Fetches and provides an observable stream of movies by categories
     */
    fun getMoviesByCategories(): LiveData<List<MoviesByCategory>>
}