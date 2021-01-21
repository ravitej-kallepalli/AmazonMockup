package com.ravitej.amazonmockup.screens.movielist.impl

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ravitej.amazonmockup.model.Movie
import com.ravitej.amazonmockup.model.MoviesByCategory
import com.ravitej.amazonmockup.screens.movielist.MovieListRepository

class MovieListRepositoryImpl : MovieListRepository {

    private var moviesLiveData: MutableLiveData<List<MoviesByCategory>> = MutableLiveData()

    //Delay for sometime and then provide the movie data
    override fun getMoviesByCategories(): LiveData<List<MoviesByCategory>> {
        generateDummyData()
        return moviesLiveData
    }

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

            (0..5).forEach {
                movies.add(Movie("Movie #$it", "201$it"))
            }

            val list = MoviesByCategory("Horror", movies)

            moviesLiveData.postValue(arrayListOf(list))
        }, 1000)
    }
}