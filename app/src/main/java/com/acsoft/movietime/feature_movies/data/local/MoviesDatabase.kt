package com.acsoft.movietime.feature_movies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acsoft.movietime.feature_movies.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}