package com.acsoft.movietime.feature_profile.domain.conversion

import com.acsoft.movietime.feature_profile.data.model.KnownForResponse
import com.acsoft.movietime.feature_profile.domain.entities.KnownFor

object ProfileConverters {

    fun convertKnownForListResponseToKnownForList(knownForListResponse: List<KnownForResponse>?): List<KnownFor> {
        return knownForListResponse?.map { knownForResponse ->
            KnownFor(
                id = knownForResponse.id ?: 0,
                title = knownForResponse.title ?: "",
                backdropPath = knownForResponse.backdropPath,
                popularity = knownForResponse.popularity,
                overview = knownForResponse.overview
            )
        } ?: emptyList()
    }

}