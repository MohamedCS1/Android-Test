package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.models.trendingMovies.Movie


@Database(entities = [Movie::class] , version = 1 , exportSchema = true)
@TypeConverters(Converters::class)
abstract class MoviesDatabase:RoomDatabase() {
    abstract fun moviesDao():MoviesDao
}
