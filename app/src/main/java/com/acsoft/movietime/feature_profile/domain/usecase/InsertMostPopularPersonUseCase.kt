package com.acsoft.movietime.feature_profile.domain.usecase

import com.acsoft.movietime.feature_profile.data.repository.PopularPersonProfileRepositoryImpl
import com.acsoft.movietime.feature_profile.domain.entities.PopularPersonProfile
import javax.inject.Inject

class InsertMostPopularPersonUseCase @Inject constructor(
    private val popularPersonProfileRepositoryImpl: PopularPersonProfileRepositoryImpl
) {
    suspend operator fun invoke(popularPersonProfile: PopularPersonProfile)  {
        popularPersonProfileRepositoryImpl.insertMostPopularPersonProfileDb(popularPersonProfile)
    }
}