package com.acsoft.movietime.feature_movies.domain.repository

import com.acsoft.movietime.feature_movies.data.model.MovieEntity
import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MoviesRepository {

    suspend fun getPopularMoviesList(page: Int): Response<MovieListResponse>

    suspend fun getRatedMoviesList(page: Int): Response<MovieListResponse>

    suspend fun getRecommendationsMoviesList(page: Int): Response<MovieListResponse>

    suspend fun getPopularMoviesListDb(): Flow<List<MovieEntity>>

    suspend fun insertPopularMoviesListDb(popularMoviesList : List<MovieEntity>)
}