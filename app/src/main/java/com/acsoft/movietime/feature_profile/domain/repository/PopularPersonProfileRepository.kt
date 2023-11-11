package com.acsoft.movietime.feature_profile.domain.repository

import com.acsoft.movietime.feature_profile.data.model.Results
import retrofit2.Response

interface PopularPersonProfileRepository {
    suspend fun getMostPopularPeopleList(): Response<Results>
}