package com.example.domain.models.detailMovie

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SpokenLanguage(
    val id:Int,
    val english_name: String,
    val iso_639_1: String,
    val name: String
){}