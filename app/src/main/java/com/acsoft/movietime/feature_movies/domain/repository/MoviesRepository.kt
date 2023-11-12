package com.acsoft.movietime.feature_movies.domain.repository

import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import retrofit2.Response

interface MoviesRepository {

    suspend fun getPopularMoviesList(page: Int): Response<MovieListResponse>

    suspend fun getRatedMoviesList(page: Int): Response<MovieListResponse>

}