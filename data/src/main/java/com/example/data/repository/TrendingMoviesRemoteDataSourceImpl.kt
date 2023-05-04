package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import com.example.domain.repository.TrendingMoviesRemoteDataSource

class TrendingMoviesRemoteDataSourceImpl(private val apiService: ApiService ,private val apiKey:String):TrendingMoviesRemoteDataSource {
    override fun getTrendingMoviesFromRemote(apiKey: String): TrendingMoviesResponse = apiService.getTrendingMovies(apiKey)
}