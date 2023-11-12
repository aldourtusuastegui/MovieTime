package com.acsoft.movietime.feature_movies.data.remote

import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieApiService {
    @GET(AppConstants.MOVIE_POPULAR)
    suspend fun getPopularMoviesList(
        @Header("Authorization") token: String = AppConstants.ACCESS_TOKEN,
        @Header("page") page: Int
    ): Response<MovieListResponse>

}