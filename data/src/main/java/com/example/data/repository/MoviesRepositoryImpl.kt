package com.example.data.repository

import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import com.example.domain.repository.*

class MoviesRepositoryImpl(private val trendingMoviesRemoteDataSource:TrendingMoviesRemoteDataSource ,private val trendingMoviesLocalDataSource: TrendingMoviesLocalDataSource ,private val cacheTrendingMovie: CacheTrendingMovie ,private val trendingMovieDetailRemoteDataSource: TrendingMovieDetailRemoteDataSource ,private val trendingMovieDetailLocalDataSource: TrendingMovieDetailLocalDataSource ,private val cacheDetailTrendingMovie: CacheDetailTrendingMovie):MoviesRepository {
    override suspend fun getRemoteTrendingMovies(apiKey: String): TrendingMoviesResponse = trendingMoviesRemoteDataSource.getTrendingMoviesFromRemote(apiKey)
    override suspend fun getLocalTrendingMovies(): List<Movie> = trendingMoviesLocalDataSource.getTrendingMoviesFromLocal()
    override suspend fun insertTrendingMovie(movie: Movie) = cacheTrendingMovie.insertTrendingMovie(movie)
    override suspend fun getTrendingMovieDetailFromRemote(apiKey: String ,movieId:Long): DetailMovieResponse = trendingMovieDetailRemoteDataSource.getTrendingMoviesDetailFromRemote(apiKey ,movieId)
    override suspend fun getLocalDetailTrendingMovies(movieId: Long): DetailMovieResponse = trendingMovieDetailLocalDataSource.getTrendingMovieDetailFromLocal(movieId)
    override suspend fun insertDetailTrendingMovie(detailMovieResponse: DetailMovieResponse) = cacheDetailTrendingMovie.insertDetailTrendingMovie(detailMovieResponse)
}