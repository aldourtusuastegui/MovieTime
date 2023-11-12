package com.acsoft.movietime.feature_movies.data.remote

import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MovieApiService {

    @GET(AppConstants.MOVIE_POPULAR)
    suspend fun getPopularMoviesList(
        @Header("Authorization") token: String = AppConstants.ACCESS_TOKEN,
        @Header("page") page: Int
    ): Response<MovieListResponse>

    @GET(AppConstants.RATED_MOVIES)
    suspend fun getRatedMoviesList(
        @Header("Authorization") token: String = AppConstants.ACCESS_TOKEN,
        @Header("page") page: Int
    ): Response<MovieListResponse>


    @GET(AppConstants.RECOMMENDATIONS)
    suspend fun getRecommendationsMoviesList(
        @Path("movie_id") movieId: Long = 678512,
        @Header("Authorization") token: String = AppConstants.ACCESS_TOKEN,
        @Header("page") page: Int
    ): Response<MovieListResponse>

}