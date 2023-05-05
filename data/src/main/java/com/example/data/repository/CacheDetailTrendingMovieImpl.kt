package com.example.data.repository

import com.example.data.local.moviesDetail.MovieDetailDao
import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.repository.CacheDetailTrendingMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CacheDetailTrendingMovieImpl(private val movieDetailDao: MovieDetailDao, private val dispatcher: CoroutineDispatcher):CacheDetailTrendingMovie {
    override suspend fun insertDetailTrendingMovie(detailMovieResponse: DetailMovieResponse) = withContext(dispatcher){ movieDetailDao.insertDetailTrendingMovie(detailMovieResponse) }
}