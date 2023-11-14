package com.acsoft.movietime.feature_movies.domain.usecase

import com.acsoft.movietime.feature_movies.domain.entities.Movie
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertRatedMoviesUseCaseTest {

    private lateinit var moviesRepository: MoviesRepository
    private lateinit var insertRatedMoviesUseCase: InsertRatedMoviesUseCase

    @Before
    fun setUp() {
        moviesRepository = mockk(relaxed = true)
        insertRatedMoviesUseCase = InsertRatedMoviesUseCase(moviesRepository)
    }

    @Test
    fun `invoke should insert rated movies into the repository`() = runBlocking {
        val ratedMoviesList = listOf(
            Movie(1,"Sheroes","/qe0oK0A5ovrlgH39Ga13dxxw9MU.jpg","2023-06-23"),
            Movie(2,"Transformers: Rise of the Beasts","/gPbM0MK8CP8A174rmUwGsADNYKD.jpg","2023-06-06")
        )

        insertRatedMoviesUseCase(ratedMoviesList)
        coVerify {
            moviesRepository.insertRatedMoviesListDb(
                any()
            )
        }
    }
}