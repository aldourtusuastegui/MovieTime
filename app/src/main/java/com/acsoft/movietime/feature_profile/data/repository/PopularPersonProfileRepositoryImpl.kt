package com.acsoft.movietime.feature_profile.data.repository

import com.acsoft.movietime.feature_profile.data.model.Results
import com.acsoft.movietime.feature_profile.data.remote.RemoteDataSource
import com.acsoft.movietime.feature_profile.domain.repository.PopularPersonProfileRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularPersonProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : PopularPersonProfileRepository {
    override suspend fun getMostPopularPeopleList(): Response<Results> {
        return remoteDataSource.getMostPopularPeopleList()
    }
}