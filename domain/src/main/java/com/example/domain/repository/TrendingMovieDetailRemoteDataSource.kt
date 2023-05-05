package com.example.domain.repository

import com.example.domain.models.detailMovie.DetailMovieResponse

interface TrendingMovieDetailRemoteDataSource {
    suspend fun getTrendingMoviesDetailFromRemote(apiKey:String ,movieId:Long):DetailMovieResponse
}