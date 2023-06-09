package com.example.data.remote

import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getTrendingMovies(@Query("api_key") apiKey:String):TrendingMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(@Path("movie_id") movieId:Long, @Query("api_key") apiKey:String ):DetailMovieResponse
}