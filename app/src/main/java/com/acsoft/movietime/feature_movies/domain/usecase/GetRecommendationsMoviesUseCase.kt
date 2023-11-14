package com.acsoft.movietime.feature_movies.domain.usecase

import android.content.Context
import com.acsoft.movietime.R
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.domain.conversion.MoviesConverter
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import com.acsoft.movietime.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecommendationsMoviesUseCase @Inject constructor(
    private val context: Context,
    private val moviesRepository: MoviesRepository
)  {
    fun invoke(): Flow<Result<MovieList>> = flow {
        if (NetworkUtils.isInternetAvailable(context)) {
            val recommendationsMoviesList = moviesRepository.getRecommendationsMoviesList(1)
            if (recommendationsMoviesList.isSuccessful) {
                emit(Result.Success(MoviesConverter.movieListResponseToMovieList(recommendationsMoviesList)))
            } else {
                emit(Result.Failure(context.getString(R.string.remote_data_failed)))
            }
        } else {
            try {
                val localRecommendationsMoviesList = moviesRepository.getRecommendationsMoviesListDb()
                localRecommendationsMoviesList.collect {
                    val moviesList = MoviesConverter.mapListOfRecommendationsMoviesEntityToListOfMovies(it)
                    emit(Result.Success(MovieList(results = moviesList)))
                }
            } catch (e: Exception) {
                emit(Result.Failure(context.getString(R.string.local_data_failed)))
            }
        }
    }
}