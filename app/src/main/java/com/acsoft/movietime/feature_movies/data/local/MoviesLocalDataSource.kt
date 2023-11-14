package com.acsoft.movietime.feature_movies.data.local

import com.acsoft.movietime.feature_movies.data.model.MovieEntity
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao
) {
    fun getPopularMoviesList() : Flow<List<MovieEntity>> {
        return moviesDao.getPopularMoviesList()
    }

    fun insertPopularMoviesList(popularMoviesList: List<MovieEntity>) {
        return moviesDao.insertPopularMoviesList(popularMoviesList)
    }

    fun getRatedMoviesList() : Flow<List<RatedMovieEntity>> {
        return moviesDao.getRatedMoviesList()
    }

    fun insertRatedMoviesList(ratedMoviesList: List<RatedMovieEntity>) {
        return moviesDao.insertRatedMoviesList(ratedMoviesList)
    }

}