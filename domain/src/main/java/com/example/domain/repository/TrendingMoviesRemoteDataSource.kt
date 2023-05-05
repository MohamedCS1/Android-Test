package com.example.domain.repository

import com.example.domain.models.trendingMovies.TrendingMoviesResponse

interface TrendingMoviesRemoteDataSource {
   suspend fun getTrendingMoviesFromRemote(apiKey:String):TrendingMoviesResponse
}