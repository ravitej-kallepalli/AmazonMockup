package com.ravitej.amazonmockup.screens.movielist.impl

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ravitej.amazonmockup.api.MovieAPI
import com.ravitej.amazonmockup.model.Movie
import com.ravitej.amazonmockup.model.MovieWrapper
import com.ravitej.amazonmockup.model.MoviesByCategory
import com.ravitej.amazonmockup.network.MOVIE_TMDB_API
import com.ravitej.amazonmockup.network.NetworkModule
import com.ravitej.amazonmockup.screens.movielist.MovieListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieListRepositoryImpl : MovieListRepository {

    private var moviesLiveData: MutableLiveData<List<MoviesByCategory>> = MutableLiveData()
    private val movieApi: MovieAPI by lazy {
        NetworkModule.getRetrofit()
            .create(MovieAPI::class.java)
    }


    //Delay for sometime and then provide the movie data
    @SuppressLint("CheckResult")
    override fun getMoviesByCategories(): LiveData<List<MoviesByCategory>> {
        movieApi.getMovies(MOVIE_TMDB_API)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                convertToCategories(it)
            }

        return moviesLiveData
    }

    private fun convertToCategories(movieWrapper: MovieWrapper) {
        val listByCategory: MutableMap<String, MutableList<Movie>> = hashMapOf()

        movieWrapper.results.forEach { movie ->
            movie.genreIds.forEach { genre ->
                if (listByCategory[genre.name] == null) {
                    listByCategory[genre.name] = mutableListOf(movie)
                } else {
                    val list = mutableListOf(movie).apply {
                        addAll(listByCategory[genre.name] as MutableList<Movie>)
                    }
                    listByCategory[genre.name] = list
                }
            }
        }

        val list: MutableList<MoviesByCategory> = mutableListOf()

        listByCategory.forEach {
            val movies: MutableList<Movie> = mutableListOf()

            it.value.forEach {
                movies.add(it)
            }

            list.add(MoviesByCategory(it.key, movies))
        }

        moviesLiveData.value = list
    }


    /*
    /**
     * Created new Thread and attached its looper to Handler.
     * Used the Handler to post some work that needs to be done in Background after sometime.
     * Once it is completed post the value to LiveData.
     */
    private fun generateDummyData() {
        val handlerThread = HandlerThread("newHandlerThread")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        handler.postDelayed({
            val movies: MutableList<Movie> = mutableListOf()

            (0..10).forEach {
                movies.add(Movie("Movie #$it", "201$it"))
            }

            val horrorList = MoviesByCategory("Horror", movies)
            val comedyList = MoviesByCategory("Comedy", movies)
            val romance = MoviesByCategory("Romance", movies)
            val action = MoviesByCategory("Action", movies)
            val amazon = MoviesByCategory("Amazon Originals", movies)

            val list = arrayListOf(horrorList, comedyList, romance, action, amazon)
            val categoryList: MutableList<MoviesByCategory> = mutableListOf()

            (0 until list.size).forEach {
                categoryList.add(list[it])
                moviesLiveData.postValue(categoryList)
                Thread.sleep(1000)
            }
        }, 1000)
    }

    */
}