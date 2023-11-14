package com.acsoft.movietime.feature_profile.data.model

import com.google.gson.annotations.SerializedName

data class KnownForResponse(
    val id: Long? = -1,
    val title: String? = "",
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    val popularity: Double? = 0.0,
    val overview: String? = ""
)