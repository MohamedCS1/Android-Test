package com.example.domain.models.detailMovie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class BelongsToCollection(
    @ColumnInfo(name = "backdropPath BelongsToCollection")
    var backdrop_path: String?,
    @ColumnInfo(name = "Id BelongsToCollection")
    val name: String?,
    @ColumnInfo(name = "poster_path BelongsToCollection")
    val poster_path: String?
)