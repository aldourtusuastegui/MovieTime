package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.domain.conversion.MoviesConverter.movieListResponseToMovieList
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    fun invoke(): Flow<Result<MovieList>> = flow {
        val ratedMoviesList = moviesRepository.getRatedMoviesList(1)
        if (ratedMoviesList.isSuccessful) {
            emit(Result.Success(movieListResponseToMovieList(ratedMoviesList)))
        } else {
            emit(Result.Failure("it was not possible to obtain remote data"))
        }
    }
}