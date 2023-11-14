package com.acsoft.movietime.feature_movies.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.usecase.InsertRecommendationsMoviesUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var insertRecommendationsMoviesUseCase: InsertRecommendationsMoviesUseCase
    private lateinit var moviesViewModel: MoviesViewModel

    @Before
    fun setUp() {
        insertRecommendationsMoviesUseCase = mockk()
        moviesViewModel = MoviesViewModel(
            getPopularMoviesUseCase = mockk(),
            getRatedMoviesUseCase = mockk(),
            getRecommendationsMoviesUseCase = mockk(),
            insertPopularMoviesUseCase = mockk(),
            insertRatedMoviesUseCase = mockk(),
            insertRecommendationsMoviesUseCase = insertRecommendationsMoviesUseCase
        )
    }

    @Test
    fun `insertRecommendationsMoviesDb should call use case with correct parameter`() =
        testScope.runBlockingTest {
            val recommendationsMoviesList = listOf(
                Movie(1,"Sheroes","/qe0oK0A5ovrlgH39Ga13dxxw9MU.jpg","2023-06-23"),
                Movie(2,"Transformers: Rise of the Beasts","/gPbM0MK8CP8A174rmUwGsADNYKD.jpg","2023-06-06"),
            )
            moviesViewModel.insertRecommendationsMoviesDb(recommendationsMoviesList)
            coVerify {
                insertRecommendationsMoviesUseCase.invoke(recommendationsMoviesList)
            }
        }
}