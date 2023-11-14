package com.acsoft.movietime.feature_movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acsoft.movietime.feature_movies.data.model.PopularMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RecommendationsMovieEntity

@Database(entities = [PopularMovieEntity::class,RatedMovieEntity::class,RecommendationsMovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}