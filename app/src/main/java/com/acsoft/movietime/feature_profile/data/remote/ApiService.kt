package com.acsoft.movietime.feature_profile.data.remote

import com.acsoft.movietime.feature_profile.data.model.Results
import com.acsoft.movietime.utils.AppConstants.ACCESS_TOKEN
import com.acsoft.movietime.utils.AppConstants.PERSON_POPULAR
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET(PERSON_POPULAR)
    suspend fun getMostPopularPeopleList(
        @Header("Authorization") token: String = ACCESS_TOKEN,
        @Header("page") page: Int = 1
    ): Response<Results>
}