package com.ravitej.amazonmockup.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val EMPTY = ""
const val DOUBLE_DEFAULT = 0.0
const val INT_DEFAULT = 0

@JsonClass(generateAdapter = true)
data class MovieWrapper(
    val page: Int = INT_DEFAULT,
    val results: List<Movie>,
    val total_results: Int = INT_DEFAULT,
    val total_pages: Int = INT_DEFAULT
)

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "poster_path") var posterPath: String = EMPTY, //optional how to handle?
    val adult: Boolean = false,
    val overview: String = EMPTY,
    val releaseDate: String = EMPTY,
    @Json(name = "genre_ids") val genreIds: List<Genre> = emptyList(),
    @Json(name = "original_title") val originalTitle: String = EMPTY,
    @Json(name = "original_language") val originalLanguage: String = EMPTY,
    @Json(name = "title") val title: String = EMPTY,
    @Json(name = "backdrop_path") val backdropPath: String? = EMPTY,
    val popularity: Double = DOUBLE_DEFAULT,
    @Json(name = "vote_count") val voteCount: Int = INT_DEFAULT,
    val video: Boolean = false,
    @Json(name = "vote_average") val voteAverage: Double = DOUBLE_DEFAULT
)

data class Genre(val id: Int, val name: String)

data class MoviesByCategory(val categoryName: String, val movies: List<Movie>)