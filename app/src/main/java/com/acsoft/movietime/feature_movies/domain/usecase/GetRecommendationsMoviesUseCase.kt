package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.domain.conversion.MoviesConverter
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecommendationsMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
)  {
    fun invoke(): Flow<Result<MovieList>> = flow {
        val recommendationsMoviesList = moviesRepository.getRecommendationsMoviesList(1)
        if (recommendationsMoviesList.isSuccessful) {
            emit(Result.Success(MoviesConverter.movieListResponseToMovieList(recommendationsMoviesList)))
        } else {
            emit(Result.Failure("it was not possible to obtain remote data"))
        }
    }
}