package com.example.domain.repository

import com.example.domain.models.trendingMovies.Movie

interface CacheMovie {
    suspend fun insertMovie(movie:Movie)
}