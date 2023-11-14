package com.acsoft.movietime.feature_movies.di

import android.content.Context
import androidx.room.Room
import com.acsoft.movietime.feature_movies.data.local.MoviesDao
import com.acsoft.movietime.feature_movies.data.local.MoviesDatabase
import com.acsoft.movietime.feature_movies.data.local.MoviesLocalDataSource
import com.acsoft.movietime.feature_movies.data.remote.MovieApiService
import com.acsoft.movietime.feature_movies.data.remote.MoviesRemoteDataSource
import com.acsoft.movietime.feature_movies.data.repository.MoviesRepositoryImpl
import com.acsoft.movietime.feature_movies.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesRemoteDataSource,moviesLocalDataSource)
    }

    //Room
    @Provides
    fun providesMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao {
        return moviesDatabase.moviesDao()
    }

    @Provides
    fun providesMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "MoviesDatabase").build()
    }

    @Singleton
    @Provides
    fun provideMoviesLocalDataSource(moviesDao: MoviesDao) = MoviesLocalDataSource(moviesDao)

}