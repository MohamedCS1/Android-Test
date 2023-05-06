package com.example.data.local.moviesDetail

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.Converters
import com.example.domain.models.detailMovie.*



@Database(entities = [DetailMovieResponse::class] , version = 11 , exportSchema = true)
@TypeConverters(Converters::class)
abstract class MoviesDetailDatabase: RoomDatabase() {
    abstract fun moviesDetailDao(): MovieDetailDao
}