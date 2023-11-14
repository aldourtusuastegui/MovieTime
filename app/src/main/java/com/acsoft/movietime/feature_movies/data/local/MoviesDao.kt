package com.acsoft.movietime.feature_movies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acsoft.movietime.feature_movies.data.model.PopularMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RatedMovieEntity
import com.acsoft.movietime.feature_movies.data.model.RecommendationsMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM PopularMovieEntity")
    fun getPopularMoviesList() : Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMoviesList(popularMoviesList: List<PopularMovieEntity>)

    @Query("SELECT * FROM RatedMovieEntity")
    fun getRatedMoviesList() : Flow<List<RatedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRatedMoviesList(ratedMoviesList: List<RatedMovieEntity>)

    @Query("SELECT * FROM RecommendationsMovieEntity")
    fun getRecommendationsMoviesList() : Flow<List<RecommendationsMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecommendationsMoviesList(recommendationsMoviesList: List<RecommendationsMovieEntity>)

}