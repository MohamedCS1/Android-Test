package com.example.domain.repository

import com.example.domain.models.trendingMovies.TrendingMoviesResponse

interface TrendingMoviesRemoteDataSource {
    fun getTrendingMoviesFromRemote():TrendingMoviesResponse
}