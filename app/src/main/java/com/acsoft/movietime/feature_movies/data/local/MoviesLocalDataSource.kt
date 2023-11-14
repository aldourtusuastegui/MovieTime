package com.acsoft.movietime.feature_movies.data.local

import com.acsoft.movietime.feature_movies.data.model.PopularMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RecommendationsMovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDao
) {
    fun getPopularMoviesList() : Flow<List<PopularMovieEntity>> {
        return moviesDao.getPopularMoviesList()
    }

    fun insertPopularMoviesList(popularMoviesList: List<PopularMovieEntity>) {
        return moviesDao.insertPopularMoviesList(popularMoviesList)
    }

    fun getRatedMoviesList() : Flow<List<RatedMovieEntity>> {
        return moviesDao.getRatedMoviesList()
    }

    fun insertRatedMoviesList(ratedMoviesList: List<RatedMovieEntity>) {
        return moviesDao.insertRatedMoviesList(ratedMoviesList)
    }

    fun getRecommendationsMoviesList() : Flow<List<RecommendationsMovieEntity>> {
        return moviesDao.getRecommendationsMoviesList()
    }

    fun insertRecommendationsMoviesList(ratedMoviesList: List<RecommendationsMovieEntity>) {
        return moviesDao.insertRecommendationsMoviesList(ratedMoviesList)
    }

}