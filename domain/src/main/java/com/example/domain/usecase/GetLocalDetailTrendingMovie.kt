package com.example.domain.usecase

import com.example.domain.repository.MoviesRepository

class GetLocalDetailTrendingMovie(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movieId:Long) = moviesRepository.getLocalDetailTrendingMovies(movieId)
}