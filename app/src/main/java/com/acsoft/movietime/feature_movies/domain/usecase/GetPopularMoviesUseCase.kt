package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.domain.conversion.MoviesConverter.movieListResponseToMovieList
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    fun invoke(): Flow<Result<MovieList>> = flow {
        val moviesListResponse = moviesRepository.getPopularMoviesList(1)
        if (moviesListResponse.isSuccessful) {
            emit(Result.Success(movieListResponseToMovieList(moviesListResponse)))
        } else {
            emit(Result.Failure("it was not possible to obtain remote data"))
        }
    }
}