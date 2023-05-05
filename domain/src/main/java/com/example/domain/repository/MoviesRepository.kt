package com.example.domain.repository

import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.models.trendingMovies.TrendingMoviesResponse

interface MoviesRepository {
    suspend fun getRemoteTrendingMovies(apiKey:String):TrendingMoviesResponse
    suspend fun getLocalTrendingMovies():List<Movie>
    suspend fun insertTrendingMovie(movie: Movie)
    suspend fun getTrendingMovieDetailFromRemote(apiKey:String ,movieId:Long):DetailMovieResponse
    suspend fun getLocalDetailTrendingMovies(movieId:Long):DetailMovieResponse
    suspend fun insertDetailTrendingMovie(detailMovieResponse: DetailMovieResponse)

}