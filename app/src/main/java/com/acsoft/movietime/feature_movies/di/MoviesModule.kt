package com.acsoft.movietime.feature_movies.di

import com.acsoft.movietime.feature_movies.data.remote.MovieApiService
import com.acsoft.movietime.feature_movies.data.remote.MoviesRemoteDataSource
import com.acsoft.movietime.feature_movies.data.repository.MoviesRepositoryImpl
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService = retrofit.create(MovieApiService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieApiService: MovieApiService) = MoviesRemoteDataSource(movieApiService)

    @Provides
    fun provideMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesRemoteDataSource)
    }

}