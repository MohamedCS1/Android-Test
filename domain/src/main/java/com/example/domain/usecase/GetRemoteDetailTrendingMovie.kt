package com.example.domain.usecase

import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.repository.MoviesRepository
import com.example.domain.repository.TrendingMovieDetailRemoteDataSource

class GetRemoteDetailTrendingMovie(private val moviesRepository: MoviesRepository) {
     suspend operator fun invoke(apiKey: String ,movieId:Long): DetailMovieResponse = moviesRepository.getTrendingMovieDetailFromRemote(apiKey ,movieId)
}