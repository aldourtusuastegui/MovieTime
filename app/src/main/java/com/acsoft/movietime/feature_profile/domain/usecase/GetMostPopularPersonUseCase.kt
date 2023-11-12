package com.acsoft.movietime.feature_profile.domain.usecase

import android.content.Context
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_profile.data.repository.PopularPersonProfileRepositoryImpl
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import com.acsoft.movietime.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMostPopularPersonUseCase @Inject constructor(
    private val context: Context,
    private val popularPersonProfileRepositoryImpl: PopularPersonProfileRepositoryImpl
) {
    fun invoke(): Flow<Result<PopularPersonProfile>> = flow {
        if (NetworkUtils.isInternetAvailable(context)) {
            val apiResponse = popularPersonProfileRepositoryImpl.getMostPopularPeopleList()
            if (apiResponse.isSuccessful) {
                val profile = apiResponse.body()?.results?.first()
                val popularPerson = PopularPersonProfile(
                    name = profile?.name,
                    popularity = profile?.popularity,
                    profilePath = profile?.profilePath
                )
                emit(Result.Success(popularPerson))
            } else {
                emit(Result.Failure("it was not possible to obtain remote data"))
            }
        } else {
            val localDataResponse = popularPersonProfileRepositoryImpl.getMostPopularPersonProfileDb()
            val profile = localDataResponse.firstOrNull()
            if (profile != null) {
                val data = PopularPersonProfile(
                    name = profile.name,
                    popularity = profile.popularity,
                    profilePath = profile.profilePath
                )
                emit(Result.Success(data))
            } else {
                emit(Result.Failure("it was not possible to obtain local data"))
            }
        }
    }
}