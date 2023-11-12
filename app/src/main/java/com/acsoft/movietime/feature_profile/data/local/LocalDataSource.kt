package com.acsoft.movietime.feature_profile.data.local

import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val profileDao: ProfileDao
) {
    fun getMostPopularPersonProfile() : Flow<ProfileEntity?> {
        return profileDao.getMostPopularPersonProfile()
    }

    fun insertMostPopularPersonProfile(profile: ProfileEntity) {
        profileDao.insertMostPopularPersonProfile(profile)
    }
}