package com.acsoft.movietime.feature_profile.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity

@Dao
interface ProfileDao {

    @Query("SELECT * FROM ProfileEntity LIMIT 1")
    fun getMostPopularPersonProfile() : LiveData<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMostPopularPersonProfile(popularPersonProfile: ProfileEntity)

}