package com.example.data.local.moviesDetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.models.detailMovie.DetailMovieResponse

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTrendingMovie(detailMovieResponse: DetailMovieResponse)

    @Query("select * from moviesDetailDatabase where id=:id")
    fun getDetailTrendingMovie(id:Long): DetailMovieResponse
}