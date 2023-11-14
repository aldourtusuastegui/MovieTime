package com.acsoft.movietime.feature_profile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acsoft.movietime.feature_profile.data.model.KnownForEntity
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM ProfileEntity LIMIT 1")
    fun getMostPopularPersonProfile(): Flow<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMostPopularPersonProfile(popularPersonProfile: ProfileEntity)

    @Query("SELECT * FROM KnownForEntity")
    fun getKnownFor(): Flow<List<KnownForEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKnownFor(knownForEntityList: List<KnownForEntity>)
}