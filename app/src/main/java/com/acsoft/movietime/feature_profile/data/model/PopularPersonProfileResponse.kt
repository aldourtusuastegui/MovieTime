package com.acsoft.movietime.feature_profile.data.model

import com.google.gson.annotations.SerializedName

data class Results(val results: List<PopularPersonProfileResponse>)

data class PopularPersonProfileResponse(
    val id: Long? = -1,
    val name: String? = "",
    val popularity: Double? = 0.0,
    @SerializedName("profile_path")
    val profilePath: String? = ""
)