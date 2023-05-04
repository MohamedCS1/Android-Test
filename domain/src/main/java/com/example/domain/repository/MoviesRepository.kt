package com.example.domain.repository

import com.example.domain.models.trendingMovies.Movie
import com.example.domain.models.trendingMovies.TrendingMoviesResponse

interface MoviesRepository {
    suspend fun getRemoteTrendingMovies(apiKey:String):TrendingMoviesResponse
    suspend fun getLocalTrendingMovies():List<Movie>
    suspend fun insertTrendingMovie(movie: Movie)
}