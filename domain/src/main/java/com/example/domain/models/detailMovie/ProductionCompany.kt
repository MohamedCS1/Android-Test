package com.example.domain.models.detailMovie

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductionCompany(
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
){}