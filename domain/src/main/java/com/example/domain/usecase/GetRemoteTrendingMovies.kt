package com.example.domain.usecase

import com.example.domain.repository.MoviesRepository

class GetRemoteTrendingMovies(private val moviesRepository: MoviesRepository ,private val apiKey:String) {
    suspend operator fun invoke() = moviesRepository.getRemoteTrendingMovies(apiKey)
}