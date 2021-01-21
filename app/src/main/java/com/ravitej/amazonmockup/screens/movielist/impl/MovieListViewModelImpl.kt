package com.ravitej.amazonmockup.screens.movielist.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravitej.amazonmockup.model.MoviesByCategory
import com.ravitej.amazonmockup.screens.movielist.MovieListRepository
import com.ravitej.amazonmockup.screens.movielist.MovieListViewModel

class MovieListViewModelImpl(
    movieListRepository: MovieListRepository
) : ViewModel(), MovieListViewModel {

    //We are keeping a reference to the Observable stream of Movies here
    //and we are going to pass the same to the View to observe.
    private var moviesList: LiveData<List<MoviesByCategory>> =
        movieListRepository.getMoviesByCategories()

    override fun getMoviesByCategories(): LiveData<List<MoviesByCategory>> {
        return moviesList
    }
}