package com.example.domain.usecase

import com.example.domain.repository.TrendingMoviesRemoteDataSource

class GetRemoteTrendingMovies(private val trendingMoviesRemoteDataSource: TrendingMoviesRemoteDataSource) {
    suspend operator fun invoke() = trendingMoviesRemoteDataSource.getTrendingMoviesFromRemote()
}