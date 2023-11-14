package com.acsoft.movietime.feature_profile.data.model

import com.google.gson.annotations.SerializedName

data class Results(val results: List<PopularPersonProfileResponse>)

data class PopularPersonProfileResponse(
    val name: String = "",
    val popularity: Double = 0.0,
    @SerializedName("profile_path")
    val profilePath: String = "",
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    @SerializedName("known_for")
    val knownFor: List<KnownForResponse> = emptyList()
)