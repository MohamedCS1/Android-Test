package com.example.data.repository

import com.example.data.local.movies.MoviesDao
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.repository.CacheTrendingMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CacheTrendingMovieImpl(private val moviesDao: MoviesDao, private val dispatcher: CoroutineDispatcher): CacheTrendingMovie {
    override suspend fun insertTrendingMovie(movie: Movie) = withContext(dispatcher) {
        moviesDao.insertTrendingMovie(movie)
    }
}