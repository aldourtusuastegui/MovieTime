package com.acsoft.movietime.feature_movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.usecase.GetPopularMoviesUseCase
import com.acsoft.movietime.feature_movies.domain.usecase.GetRatedMoviesUseCase
import com.acsoft.movietime.feature_movies.domain.usecase.GetRecommendationsMoviesUseCase
import com.acsoft.movietime.feature_movies.domain.usecase.InsertPopularMoviesUseCase
import com.acsoft.movietime.feature_movies.domain.usecase.InsertRatedMoviesUseCase
import com.acsoft.movietime.feature_movies.domain.usecase.InsertRecommendationsMoviesUseCase
import com.acsoft.movietime.utils.AppConstants.AN_ERROR_OCCURRED
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getRatedMoviesUseCase: GetRatedMoviesUseCase,
    private val getRecommendationsMoviesUseCase: GetRecommendationsMoviesUseCase,
    private val insertPopularMoviesUseCase: InsertPopularMoviesUseCase,
    private val insertRatedMoviesUseCase: InsertRatedMoviesUseCase,
    private val insertRecommendationsMoviesUseCase: InsertRecommendationsMoviesUseCase
) : ViewModel() {

    private val _popularMoviesList = MutableLiveData<Result<MovieList>>()
    val popularMoviesList: LiveData<Result<MovieList>> get() = _popularMoviesList

    private val _ratedMoviesList = MutableLiveData<Result<MovieList>>()
    val ratedMoviesList: LiveData<Result<MovieList>> get() = _ratedMoviesList

    private val _recommendationsMoviesList = MutableLiveData<Result<MovieList>>()
    val recommendationsMoviesList: LiveData<Result<MovieList>> get() = _recommendationsMoviesList

    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _popularMoviesList.value = Result.Loading
                getPopularMoviesUseCase.invoke().collect { result ->
                    _popularMoviesList.value = result
                }
            } catch (e: Exception) {
                _popularMoviesList.value = Result.Failure(AN_ERROR_OCCURRED)
            }
        }
    }

    fun getRatedMovies() {
        viewModelScope.launch {
            try {
                _ratedMoviesList.value = Result.Loading
                getRatedMoviesUseCase.invoke().collect { result ->
                    _ratedMoviesList.value = result
                }
            } catch (e: Exception) {
                _ratedMoviesList.value = Result.Failure(AN_ERROR_OCCURRED)
            }
        }
    }

    fun getRecommendationsMovies() {
        viewModelScope.launch {
            try {
                _recommendationsMoviesList.value = Result.Loading
                getRecommendationsMoviesUseCase.invoke().collect { result ->
                    _recommendationsMoviesList.value = result
                }
            } catch (e: Exception) {
                _recommendationsMoviesList.value = Result.Failure(AN_ERROR_OCCURRED)
            }
        }
    }

    fun insertPopularMoviesDb(popularMoviesList: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            insertPopularMoviesUseCase.invoke(popularMoviesList)
        }
    }

    fun insertRatedMoviesDb(ratedMoviesList: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            insertRatedMoviesUseCase.invoke(ratedMoviesList)
        }
    }

    fun insertRecommendationsMoviesDb(recommendationsMoviesList: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            insertRecommendationsMoviesUseCase.invoke(recommendationsMoviesList)
        }
    }
}