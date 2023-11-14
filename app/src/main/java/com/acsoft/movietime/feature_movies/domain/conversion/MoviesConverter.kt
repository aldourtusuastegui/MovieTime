package com.acsoft.movietime.feature_movies.domain.conversion

import com.acsoft.movietime.feature_movies.data.model.PopularMovieEntity
import com.acsoft.movietime.feature_movies.data.model.MovieListResponse
import com.acsoft.movietime.feature_movies.data.model.MovieResponse
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RecommendationsMovieEntity
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
    fun mapListOfMoviesToListOfMoviesEntity(moviesList: List<Movie>): List<PopularMovieEntity> {
        return moviesList.map { movie ->
            PopularMovieEntity(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate
            )
        }
    }

    //convert List of MovieEntity to List of Movies
    fun mapListOfMoviesEntityToListOfMovies(popularMovieEntityList: List<PopularMovieEntity>): List<Movie> {
        return popularMovieEntityList.map { movieEntity ->
            Movie(
                id = movieEntity.id,
                title = movieEntity.title,
                posterPath = movieEntity.posterPath,
                releaseDate = movieEntity.releaseDate
            )
        }
    }

    fun mapListOfRatedMoviesEntityToListOfMovies(ratedMoviesEntityList: List<RatedMovieEntity>): List<Movie> {
        return ratedMoviesEntityList.map {
            Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate
            )
        }
    }

    fun mapListOfMoviesToListOfRatedMovieEntity(moviesList: List<Movie>): List<RatedMovieEntity> {
        return moviesList.map {
            RatedMovieEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate
            )
        }
    }

    fun mapListOfRecommendationsMoviesEntityToListOfMovies(recommendationsMoviesEntityList: List<RecommendationsMovieEntity>): List<Movie> {
        return recommendationsMoviesEntityList.map {
            Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate
            )
        }
    }

    fun mapListOfMoviesToListOfRecommendationsMovieEntity(moviesList: List<Movie>): List<RecommendationsMovieEntity> {
        return moviesList.map {
            RecommendationsMovieEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate
            )
        }
    }
}
