package com.example.domain.repository

import com.example.domain.models.trendingMovies.Movie

interface TrendingMoviesLocalDataSource {
    suspend fun getTrendingMoviesFromLocal():List<Movie>
}