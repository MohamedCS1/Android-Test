package com.example.domain.repository

import com.example.domain.models.trendingMovies.Movie

interface CacheTrendingMovie {
    suspend fun insertTrendingMovie(movie:Movie)
}