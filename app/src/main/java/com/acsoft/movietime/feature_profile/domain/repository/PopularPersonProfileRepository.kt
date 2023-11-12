package com.acsoft.movietime.feature_profile.domain.repository

import androidx.lifecycle.LiveData
import com.acsoft.movietime.feature_profile.data.model.ProfileEntity
import com.acsoft.movietime.feature_profile.data.model.Results
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import retrofit2.Response

interface PopularPersonProfileRepository {
    suspend fun getMostPopularPeopleList(): Response<Results>

    suspend fun getMostPopularPersonProfileDb() : LiveData<ProfileEntity>

    suspend fun insertMostPopularPersonProfileDb(popularPersonProfile: PopularPersonProfile)
}