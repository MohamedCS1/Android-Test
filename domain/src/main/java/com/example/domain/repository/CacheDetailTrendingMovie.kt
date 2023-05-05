package com.example.domain.repository

import com.example.domain.models.detailMovie.DetailMovieResponse

interface CacheDetailTrendingMovie {
    suspend fun insertDetailTrendingMovie(detailMovieResponse: DetailMovieResponse)
}