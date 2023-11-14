package com.acsoft.movietime.feature_profile.data.local

import com.acsoft.movietime.feature_profile.data.model.KnownForEntity
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val profileDao: ProfileDao
) {
    fun getMostPopularPersonProfile() : Flow<ProfileEntity> {
        return profileDao.getMostPopularPersonProfile()
    }

    fun insertMostPopularPersonProfile(profile: ProfileEntity) {
        profileDao.insertMostPopularPersonProfile(profile)
    }

    fun getKnownFor() : Flow<List<KnownForEntity>> {
        return profileDao.getKnownFor()
    }

    fun insertKnownFor(knownForEntityList: List<KnownForEntity>) {
        profileDao.insertKnownFor(knownForEntityList)
    }
}