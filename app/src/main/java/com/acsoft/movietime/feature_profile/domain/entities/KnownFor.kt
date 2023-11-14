package com.acsoft.movietime.feature_profile.domain.entities

data class KnownFor(
    val id: Long? = -1,
    val title: String? = "",
    val backdropPath: String? = "",
    val popularity: Double? = 0.0,
    val overview: String? = ""
)