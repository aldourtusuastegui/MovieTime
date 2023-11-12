package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.feature_movies.data.model.MovieResponse
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
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

    private fun movieListResponseToMovieList(movieListResponse: Response<MovieListResponse>) : MovieList {
        movieListResponse.body()?.results
        return MovieList(
            page = movieListResponse.body()?.page,
            results =  movieResponseToMovie(movieListResponse.body()?.results),
            totalPages = movieListResponse.body()?.totalPages
        )
    }

    private fun movieResponseToMovie(movieResponse: List<MovieResponse>?) : List<Movie> {
        return movieResponse?.map { response ->
            Movie(
                id = response.id,
                title = response.title,
                posterPath = response.posterPath,
                releaseDate = response.releaseDate
            )
        } ?: listOf()
    }
}