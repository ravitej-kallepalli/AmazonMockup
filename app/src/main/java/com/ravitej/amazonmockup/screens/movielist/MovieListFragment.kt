package com.ravitej.amazonmockup.screens.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravitej.amazonmockup.R
import com.ravitej.amazonmockup.databinding.FragmentMovieListBinding
import com.ravitej.amazonmockup.screens.movielist.adapter.MovieListCategoryAdapter
import com.ravitej.amazonmockup.screens.movielist.impl.MovieListViewModelImpl

/**
 * MovieListFragment is a View responsible for inflating MovieListLayout and binding data to it.
 */
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var moviesCategoryAdapter: MovieListCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        init()
        return binding.root
    }

    private fun init() {
        viewModel = ViewModelProvider(
            this,
            MovieListViewModelFactory()
        ).get(MovieListViewModelImpl::class.java)

        moviesCategoryAdapter = MovieListCategoryAdapter()
        binding.moviesByCategoryRv.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesByCategoryRv.adapter = moviesCategoryAdapter

        observerViewModel()
    }

    //LiveData will start sending the results only when it is being observed.
    private fun observerViewModel() {
        viewModel.getMoviesByCategories()
            .observe(this, Observer { moviesList ->
                moviesCategoryAdapter.submitList(moviesList)
            })
    }

    companion object {

        /**
         * Create new instance every time, movie list fragment is requested.
         */
        fun getInstance(): MovieListFragment {
            return MovieListFragment()
        }
    }
}