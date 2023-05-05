package com.example.androidtest.di

import com.example.data.local.moviesDetail.MovieDetailDao
import com.example.data.local.movies.MoviesDao
import com.example.data.remote.ApiService
import com.example.data.repository.*
import com.example.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    fun provideRemoteTrendingMovieRepository(apiService: ApiService):TrendingMoviesRemoteDataSource
    {
        return TrendingMoviesRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideLocalTrendingMovieRepository(moviesDao: MoviesDao, dispatcher: CoroutineDispatcher):TrendingMoviesLocalDataSource
    {
        return TrendingMoviesLocalDataSourceImpl(moviesDao ,dispatcher)
    }

    @Provides
    fun provideCacheTrendingMovie(moviesDao: MoviesDao, dispatcher: CoroutineDispatcher):CacheTrendingMovie
    {
        return CacheTrendingMovieImpl(moviesDao ,dispatcher)
    }

    @Provides
    fun provideCacheDetailTrendingMovie(movieDetailDao: MovieDetailDao, dispatcher: CoroutineDispatcher):CacheDetailTrendingMovie
    {
        return CacheDetailTrendingMovieImpl(movieDetailDao ,dispatcher)
    }

    @Provides
    fun provideRemoteDetailTrendingMovieRepository(apiService: ApiService): TrendingMovieDetailRemoteDataSource
    {
        return TrendingMovieDetailRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideLocalDetailTrendingMovieRepository(movieDetailDao: MovieDetailDao, dispatcher: CoroutineDispatcher):TrendingMovieDetailLocalDataSource
    {
        return TrendingMovieDetailLocalDataSourceImpl(movieDetailDao ,dispatcher)
    }

    @Provides
    fun provideMoviesRepository(trendingMoviesRemoteDataSource: TrendingMoviesRemoteDataSource, trendingMoviesLocalDataSource: TrendingMoviesLocalDataSource, cacheTrendingMovie: CacheTrendingMovie ,trendingMovieDetailLocalDataSource: TrendingMovieDetailLocalDataSource  ,trendingMovieDetailRemoteDataSource: TrendingMovieDetailRemoteDataSource ,cacheDetailTrendingMovie: CacheDetailTrendingMovie):MoviesRepository
    {
        return MoviesRepositoryImpl(trendingMoviesRemoteDataSource, trendingMoviesLocalDataSource, cacheTrendingMovie ,trendingMovieDetailRemoteDataSource ,trendingMovieDetailLocalDataSource ,cacheDetailTrendingMovie)
    }
}