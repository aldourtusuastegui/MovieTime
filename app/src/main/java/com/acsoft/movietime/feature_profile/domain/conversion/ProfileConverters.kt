package com.acsoft.movietime.feature_profile.domain.conversion

import com.acsoft.movietime.feature_profile.data.model.KnownForEntity
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

    fun List<KnownFor>.toKnownForEntityList(): List<KnownForEntity> {
        return this.map {
            KnownForEntity(
                id = it.id ?: -1,
                title = it.title.orEmpty(),
                backdropPath = it.backdropPath.orEmpty(),
                popularity = it.popularity ?: 0.0,
                overview = it.overview.orEmpty()
            )
        }
    }

    fun List<KnownForEntity>.toKnownForList(): List<KnownFor> {
        return this.map {
            KnownFor(
                id = it.id ?: -1,
                title = it.title.orEmpty(),
                backdropPath = it.backdropPath.orEmpty(),
                popularity = it.popularity ?: 0.0,
                overview = it.overview.orEmpty()
            )
        }
    }

}