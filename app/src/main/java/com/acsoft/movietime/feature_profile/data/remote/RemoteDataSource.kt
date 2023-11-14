package com.acsoft.movietime.feature_profile.data.remote

import com.acsoft.movietime.feature_profile.data.model.Results
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getMostPopularPeopleList() : Response<Results>  {
        return apiService.getMostPopularPeopleList()
    }
}