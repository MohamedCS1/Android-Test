package com.example.domain.usecase

import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.repository.MoviesRepository

class CacheDetailTrendingMovie(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(detailMovieResponse:DetailMovieResponse) = moviesRepository.insertDetailTrendingMovie(detailMovieResponse)
}