package com.acsoft.movietime.feature_movies.data.repository

import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.feature_movies.data.remote.MoviesRemoteDataSource
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    ): MoviesRepository {
    override suspend fun getPopularMoviesList(page: Int): Response<MovieListResponse> {
        return moviesRemoteDataSource.getPopularMoviesList(page)
    }
}