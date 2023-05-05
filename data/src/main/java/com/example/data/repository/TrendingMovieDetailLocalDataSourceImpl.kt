package com.example.data.repository

import com.example.data.local.moviesDetail.MovieDetailDao
import com.example.domain.models.detailMovie.DetailMovieResponse
import com.example.domain.repository.TrendingMovieDetailLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TrendingMovieDetailLocalDataSourceImpl(private val movieDetailDao: MovieDetailDao, private val dispatcher: CoroutineDispatcher):TrendingMovieDetailLocalDataSource {
    override suspend fun getTrendingMovieDetailFromLocal(movieId:Long): DetailMovieResponse = withContext(dispatcher){movieDetailDao.getDetailTrendingMovie(movieId)}
}