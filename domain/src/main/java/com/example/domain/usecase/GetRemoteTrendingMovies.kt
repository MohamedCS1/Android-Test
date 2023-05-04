package com.example.domain.usecase

import com.example.domain.repository.MoviesRepository

class GetRemoteTrendingMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() = moviesRepository.getRemoteTrendingMovies()
}