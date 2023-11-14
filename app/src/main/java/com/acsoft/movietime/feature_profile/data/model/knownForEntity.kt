package com.acsoft.movietime.feature_profile.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KnownForEntity(
    @PrimaryKey
    val id: Long?,
    val title: String,
    val backdropPath: String,
    val popularity: Double,
    val overview: String
)