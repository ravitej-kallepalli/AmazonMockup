package com.ravitej.amazonmockup.screens.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ravitej.amazonmockup.screens.movielist.impl.MovieListRepositoryImpl
import com.ravitej.amazonmockup.screens.movielist.impl.MovieListViewModelImpl

/**
 * This class will be used to create the ViewModel.
 * If there is a ViewModel already created this class #create method won't be invoked.
 */
class MovieListViewModelFactory : ViewModelProvider.Factory {

    /**
     * Currently, MovieListRepository is at FragmentScope.
     */
    private val repository: MovieListRepository = MovieListRepositoryImpl()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModelImpl(repository) as T
    }
}