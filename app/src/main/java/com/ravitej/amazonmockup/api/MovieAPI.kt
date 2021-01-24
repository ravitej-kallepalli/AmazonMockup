package com.ravitej.amazonmockup.api

import com.ravitej.amazonmockup.model.MovieWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") apiKey: String?): Observable<MovieWrapper>
}