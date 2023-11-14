package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.feature_movies.domain.conversion.MoviesConverter
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import javax.inject.Inject

class InsertRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
)  {
    suspend operator fun invoke(ratedMoviesList: List<Movie>)  {
        moviesRepository.insertRatedMoviesListDb(MoviesConverter.mapListOfMoviesToListOfRatedMovieEntity(ratedMoviesList))
    }
}