package com.example.domain.repository

import com.example.domain.models.detailMovie.DetailMovieResponse

interface TrendingMovieDetailLocalDataSource {
    suspend fun getTrendingMovieDetailFromLocal(movieId:Long):DetailMovieResponse
}