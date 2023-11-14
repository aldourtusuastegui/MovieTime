package com.acsoft.movietime.feature_profile.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.acsoft.movietime.feature_profile.data.model.KnownForEntity
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity

@Database(entities = [ProfileEntity::class, KnownForEntity::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}