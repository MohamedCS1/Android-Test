package com.example.domain.usecase

import com.example.domain.repository.MoviesRepository

class GetLocalTrendingMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() = moviesRepository.getLocalTrendingMovies()
}