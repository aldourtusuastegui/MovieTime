package com.acsoft.movietime.feature_profile.domain.usecase

import com.acsoft.movietime.core.Result
import com.acsoft.movietime.feature_profile.data.repository.PopularPersonProfileRepositoryImpl
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import javax.inject.Inject

class GetMostPopularPersonUseCase @Inject constructor(
    private val popularPersonProfileRepositoryImpl: PopularPersonProfileRepositoryImpl
) {
    suspend operator fun invoke() : Result<PopularPersonProfile>  {
        val results = popularPersonProfileRepositoryImpl.getMostPopularPeopleList()
        return if (results.isSuccessful) {
            val data = results.body()?.results?.first()
            val popularPerson = PopularPersonProfile(
                data?.id,
                data?.name,
                data?.popularity,
                data?.profilePath
            )
            Result.Success(popularPerson)
        } else {
            Result.Failure("we had issues to get popular person")
        }
    }
}