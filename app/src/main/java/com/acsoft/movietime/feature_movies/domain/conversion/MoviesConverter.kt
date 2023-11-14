package com.acsoft.movietime.feature_movies.domain.conversion

import com.acsoft.movietime.feature_movies.data.model.MovieEntity
import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.feature_movies.data.model.MovieResponse
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import retrofit2.Response

object MoviesConverter {

    fun movieListResponseToMovieList(movieListResponse: Response<MovieListResponse>) : MovieList {
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

    //convert List of Movies to List of MovieEntity
    fun mapListOfMoviesToListOfMoviesEntity(moviesList: List<Movie>): List<MovieEntity> {
        return moviesList.map { movie ->
            MovieEntity(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate
            )
        }
    }

    //convert List of MovieEntity to List of Movies
    fun mapListOfMoviesEntityToListOfMovies(movieEntityList: List<MovieEntity>): List<Movie> {
        return movieEntityList.map { movieEntity ->
            Movie(
                id = movieEntity.id,
                title = movieEntity.title,
                posterPath = movieEntity.posterPath,
                releaseDate = movieEntity.releaseDate
            )
        }
    }
}
