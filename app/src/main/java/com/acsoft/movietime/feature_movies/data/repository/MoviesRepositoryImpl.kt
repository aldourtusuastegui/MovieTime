package com.acsoft.movietime.feature_movies.data.repository

import com.acsoft.movietime.feature_movies.data.local.MoviesLocalDataSource
import com.acsoft.movietime.feature_movies.data.model.MovieEntity
import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RecommendationsMovieEntity
import com.acsoft.movietime.feature_movies.data.remote.MoviesRemoteDataSource
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesRepository {

    override suspend fun getPopularMoviesList(page: Int): Response<MovieListResponse> {
        return moviesRemoteDataSource.getPopularMoviesList(page)
    }

    override suspend fun getRatedMoviesList(page: Int): Response<MovieListResponse> {
        return moviesRemoteDataSource.getRatedMoviesList(page)
    }

    override suspend fun getRecommendationsMoviesList(page: Int): Response<MovieListResponse> {
        return moviesRemoteDataSource.getRecommendationsMoviesList(page)
    }

    override fun getPopularMoviesListDb(): Flow<List<MovieEntity>> {
        return moviesLocalDataSource.getPopularMoviesList()
    }

    override fun getRatedMoviesListDb(): Flow<List<RatedMovieEntity>> {
        return moviesLocalDataSource.getRatedMoviesList()
    }

    override fun getRecommendationsMoviesListDb(): Flow<List<RecommendationsMovieEntity>> {
        return moviesLocalDataSource.getRecommendationsMoviesList()
    }

    override suspend fun insertPopularMoviesListDb(popularMoviesList: List<MovieEntity>) {
        moviesLocalDataSource.insertPopularMoviesList(popularMoviesList)
    }

    override suspend fun insertRatedMoviesListDb(ratedMoviesList: List<RatedMovieEntity>) {
        moviesLocalDataSource.insertRatedMoviesList(ratedMoviesList)
    }

    override suspend fun insertRecommendationsMoviesListDb(recommendationsMoviesList: List<RecommendationsMovieEntity>) {
        moviesLocalDataSource.insertRecommendationsMoviesList(recommendationsMoviesList)
    }
}