package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.feature_movies.domain.conversion.MoviesConverter
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import javax.inject.Inject

class InsertPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(popularMoviesList: List<Movie>)  {
        moviesRepository.insertPopularMoviesListDb(MoviesConverter.mapListOfMoviesToListOfMoviesEntity(popularMoviesList))
    }
}