package com.acsoft.movietime.feature_profile.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileEntity(
    @PrimaryKey val id: Long? = 1,
    val name: String?,
    val popularity: Double?,
    val profilePath: String?
)
