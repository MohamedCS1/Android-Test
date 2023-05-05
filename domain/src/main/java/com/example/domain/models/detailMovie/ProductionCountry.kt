package com.example.domain.models.detailMovie

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductionCountry(
    val id:Int?,
    val iso_3166_1: String?,
    val name: String
){}