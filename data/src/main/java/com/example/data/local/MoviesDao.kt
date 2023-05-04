package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.models.trendingMovies.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrendingMovie(movie:Movie)

    @Query("select * from moviesDatabase")
    fun getAllTrendingMovies():List<Movie>

}