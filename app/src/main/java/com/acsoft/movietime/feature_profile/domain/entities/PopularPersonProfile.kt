package com.acsoft.movietime.feature_profile.domain.entities

data class PopularPersonProfile(
    val id: Long? = 1,
    val name: String? = "",
    val popularity: Double? = 0.0,
    val profilePath: String? = ""
)
