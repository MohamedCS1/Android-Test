package com.example.data.repository

import com.example.data.remote.ApiService
import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.repository.TrendingMovieDetailRemoteDataSource


class TrendingMovieDetailRemoteDataSourceImpl(private val apiService: ApiService):TrendingMovieDetailRemoteDataSource {
    override suspend fun getTrendingMoviesDetailFromRemote(apiKey: String, movieId: Long): DetailMovieResponse = apiService.getMovieDetailsById(movieId, apiKey)
}