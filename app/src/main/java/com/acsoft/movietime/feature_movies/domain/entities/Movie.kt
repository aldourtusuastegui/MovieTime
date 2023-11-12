package com.acsoft.movietime.feature_movies.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String
)