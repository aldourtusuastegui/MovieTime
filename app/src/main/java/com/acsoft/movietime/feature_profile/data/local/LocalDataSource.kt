package com.acsoft.movietime.feature_profile.data.local

import androidx.lifecycle.LiveData
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val profileDao: ProfileDao
) {
    fun getMostPopularPersonProfile() : LiveData<ProfileEntity> {
        return profileDao.getMostPopularPersonProfile()
    }

    fun insertMostPopularPersonProfile(profile: ProfileEntity) {
        profileDao.insertMostPopularPersonProfile(profile)
    }
}