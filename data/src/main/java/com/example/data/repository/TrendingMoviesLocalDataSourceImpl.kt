package com.example.data.repository

import com.example.data.local.movies.MoviesDao
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.repository.TrendingMoviesLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TrendingMoviesLocalDataSourceImpl(private val moviesDao: MoviesDao, private val dispatcher: CoroutineDispatcher):TrendingMoviesLocalDataSource {
    override suspend fun getTrendingMoviesFromLocal(): List<Movie> = withContext(dispatcher){
        moviesDao.getAllTrendingMovies()
    }
}