package com.acsoft.movietime.feature_movies.data.remote

import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val movieApiService: MovieApiService
) {
    suspend fun getPopularMoviesList(page: Int) : Response<MovieListResponse> {
        return movieApiService.getPopularMoviesList(page = page)
    }

    suspend fun getRatedMoviesList(page: Int) : Response<MovieListResponse> {
        return movieApiService.getRatedMoviesList(page = page)
    }
}