package com.acsoft.movietime.feature_profile.data.repository

import androidx.lifecycle.LiveData
import com.acsoft.movietime.feature_profile.data.local.LocalDataSource
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import com.acsoft.movietime.feature_profile.data.model.Results
import com.acsoft.movietime.feature_profile.data.remote.RemoteDataSource
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import com.acsoft.movietime.feature_profile.domain.repository.PopularPersonProfileRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPersonProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PopularPersonProfileRepository {
    override suspend fun getMostPopularPeopleList(): Response<Results> {
        return remoteDataSource.getMostPopularPeopleList()
    }

    override suspend fun getMostPopularPersonProfileDb(): LiveData<ProfileEntity> {
        return localDataSource.getMostPopularPersonProfile()
    }

    override suspend fun insertMostPopularPersonProfileDb(popularPersonProfile: PopularPersonProfile) {
        val profileEntity = mapPopularPersonProfileToProfileEntity(popularPersonProfile)
        localDataSource.insertMostPopularPersonProfile(profileEntity)
    }

    private fun mapPopularPersonProfileToProfileEntity(popularPersonProfile: PopularPersonProfile): ProfileEntity {
        return ProfileEntity(
            id = popularPersonProfile.id,
            name = popularPersonProfile.name,
            popularity = popularPersonProfile.popularity,
            profilePath = popularPersonProfile.profilePath
        )
    }
}