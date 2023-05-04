package com.example.domain.usecase

import com.example.domain.models.trendingMovies.Movie
import com.example.domain.repository.MoviesRepository

class CacheMovie(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movie:Movie) = moviesRepository.insertMovie(movie)
}