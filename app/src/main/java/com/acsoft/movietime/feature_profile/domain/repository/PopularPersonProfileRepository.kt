package com.acsoft.movietime.feature_profile.domain.repository

import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import com.acsoft.movietime.feature_profile.data.model.Results
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PopularPersonProfileRepository {
    suspend fun getMostPopularPeopleList(): Response<Results>

    fun getMostPopularPersonProfileDb() : Flow<ProfileEntity?>

    suspend fun insertMostPopularPersonProfileDb(popularPersonProfile: PopularPersonProfile)
}