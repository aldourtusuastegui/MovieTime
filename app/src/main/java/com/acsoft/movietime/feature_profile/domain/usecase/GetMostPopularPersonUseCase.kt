package com.acsoft.movietime.feature_profile.domain.usecase

import android.content.Context
import com.acsoft.movietime.R
import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_profile.domain.conversion.ProfileConverters.convertKnownForListResponseToKnownForList
import com.acsoft.movietime.feature_profile.domain.conversion.ProfileConverters.toKnownForList
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import com.acsoft.movietime.feature_profile.domain.repository.PopularPersonProfileRepository
import com.acsoft.movietime.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMostPopularPersonUseCase @Inject constructor(
    private val context: Context,
    private val popularPersonProfileRepository: PopularPersonProfileRepository
) {
    fun invoke(): Flow<Result<PopularPersonProfile>> = flow {
        if (NetworkUtils.isInternetAvailable(context)) {
            val apiResponse = popularPersonProfileRepository.getMostPopularPeopleList()
            if (apiResponse.isSuccessful) {
                val profile = apiResponse.body()?.results?.first()
                val knownForResponseList = profile?.knownFor
                val popularPerson = PopularPersonProfile(
                    name = profile?.name,
                    popularity = profile?.popularity,
                    profilePath = profile?.profilePath,
                    knownForDepartment = profile?.knownForDepartment,
                    knownFor = convertKnownForListResponseToKnownForList(knownForResponseList)
                )
                emit(Result.Success(popularPerson))
            } else {
                emit(Result.Failure(context.getString(R.string.remote_data_failed)))
            }
        } else {
            val localDataResponse = popularPersonProfileRepository.getMostPopularPersonProfileDb()
            val localDataKnownForList = popularPersonProfileRepository.getKnownForDb().first()
            val profile = localDataResponse.firstOrNull()
            if (profile != null) {
                val data = PopularPersonProfile(
                    name = profile.name,
                    popularity = profile.popularity,
                    profilePath = profile.profilePath,
                    knownForDepartment = profile.knownForDepartment,
                    knownFor = localDataKnownForList?.toKnownForList()
                )
                emit(Result.Success(data))
            } else {
                emit(Result.Failure(context.getString(R.string.local_data_failed)))
            }
        }
    }
}