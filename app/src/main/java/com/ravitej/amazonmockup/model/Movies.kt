package com.ravitej.amazonmockup.model

data class Movie(val name: String, val releasedDate: String, val imageUrl: String? = null)

data class MoviesByCategory(val categoryName: String, val movies: List<Movie>)
