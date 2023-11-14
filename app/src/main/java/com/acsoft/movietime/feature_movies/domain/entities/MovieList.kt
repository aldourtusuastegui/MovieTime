package com.acsoft.movietime.feature_movies.domain.entities

data class MovieList(
    val page: Int? = 1,
    val results: List<Movie>? = listOf(),
    val totalPages: Int? = 1
)
