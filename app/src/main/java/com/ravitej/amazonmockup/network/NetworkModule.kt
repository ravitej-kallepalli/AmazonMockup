package com.ravitej.amazonmockup.network

import com.ravitej.amazonmockup.adapters.GenreAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val MOVIE_API = "https://api.themoviedb.org/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/w185";

object NetworkModule {

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(MOVIE_API)
            .client(okHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(GenreAdapter)
            .build()
    }

    fun getRetrofit(): Retrofit {
        return retrofitBuilder
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }
}