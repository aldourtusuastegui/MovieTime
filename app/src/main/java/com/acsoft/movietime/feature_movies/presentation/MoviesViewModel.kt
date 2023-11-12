package com.acsoft.movietime.feature_movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_movies.domain.entities.MovieList
import com.acsoft.movietime.feature_movies.domain.usecase.GetPopularMoviesUseCase
import com.acsoft.movietime.feature_movies.domain.usecase.GetRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getRatedMoviesUseCase: GetRatedMoviesUseCase
) : ViewModel() {

    private val _popularMoviesList = MutableLiveData<Result<MovieList>>()
    val popularMoviesList: LiveData<Result<MovieList>> get() = _popularMoviesList

    private val _ratedMoviesList = MutableLiveData<Result<MovieList>>()
    val ratedMoviesList: LiveData<Result<MovieList>> get() = _ratedMoviesList


    fun getPopularMovies() {
        viewModelScope.launch {
            try {
                _popularMoviesList.value = Result.Loading
                getPopularMoviesUseCase.invoke().collect { result ->
                    _popularMoviesList.value = result
                }
            } catch (e: Exception) {
                _popularMoviesList.value = Result.Failure("An unexpected error occurred: ${e.message}")
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
                _ratedMoviesList.value = Result.Failure("An unexpected error occurred: ${e.message}")
            }
        }
    }
}