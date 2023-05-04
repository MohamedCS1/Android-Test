package com.example.data.repository

import com.example.domain.models.trendingMovies.Movie
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import com.example.domain.repository.CacheTrendingMovie
import com.example.domain.repository.MoviesRepository
import com.example.domain.repository.TrendingMoviesLocalDataSource
import com.example.domain.repository.TrendingMoviesRemoteDataSource

class MoviesRepositoryImpl(private val trendingMoviesRemoteDataSource:TrendingMoviesRemoteDataSource ,private val trendingMoviesLocalDataSource: TrendingMoviesLocalDataSource ,private val cacheTrendingMovie: CacheTrendingMovie):MoviesRepository {
    override suspend fun getRemoteTrendingMovies(apiKey: String): TrendingMoviesResponse = trendingMoviesRemoteDataSource.getTrendingMoviesFromRemote(apiKey)

    override suspend fun getLocalTrendingMovies(): List<Movie> = trendingMoviesLocalDataSource.getTrendingMoviesFromLocal()

    override suspend fun insertTrendingMovie(movie: Movie) = cacheTrendingMovie.insertTrendingMovie(movie)
}