package com.acsoft.movietime.feature_movies.domain.repository

import com.acsoft.movietime.feature_movies.data.model.PopularMovieEntity
import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RecommendationsMovieEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MoviesRepository {

    suspend fun getPopularMoviesList(page: Int): Response<MovieListResponse>

    suspend fun getRatedMoviesList(page: Int): Response<MovieListResponse>

    suspend fun getRecommendationsMoviesList(page: Int): Response<MovieListResponse>

    fun getPopularMoviesListDb(): Flow<List<PopularMovieEntity>>

    fun getRatedMoviesListDb(): Flow<List<RatedMovieEntity>>

    fun getRecommendationsMoviesListDb(): Flow<List<RecommendationsMovieEntity>>

    suspend fun insertPopularMoviesListDb(popularMoviesList : List<PopularMovieEntity>)

    suspend fun insertRatedMoviesListDb(ratedMoviesList : List<RatedMovieEntity>)

    suspend fun insertRecommendationsMoviesListDb(recommendationsMoviesList : List<RecommendationsMovieEntity>)

}