package com.example.data.local.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.Converters
import com.example.domain.models.detailMovie.*
import com.example.domain.models.trendingMovies.Movie


@Database(entities = [Movie::class], version = 9 , exportSchema = true)
@TypeConverters(Converters::class)
abstract class MoviesDatabase:RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
