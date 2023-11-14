package com.acsoft.movietime.feature_movies.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
)


@Entity
data class RatedMovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
)


