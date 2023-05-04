package com.example.androidtest.di

import com.example.data.local.MoviesDao
import com.example.data.remote.ApiService
import com.example.data.repository.CacheTrendingMovieImpl
import com.example.data.repository.MoviesRepositoryImpl
import com.example.data.repository.TrendingMoviesLocalDataSourceImpl
import com.example.data.repository.TrendingMoviesRemoteDataSourceImpl
import com.example.domain.repository.CacheTrendingMovie
import com.example.domain.repository.MoviesRepository
import com.example.domain.repository.TrendingMoviesLocalDataSource
import com.example.domain.repository.TrendingMoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    fun provideRemoteTrendingMovieRepository(apiService: ApiService ,apiKey:String):TrendingMoviesRemoteDataSource
    {
        return TrendingMoviesRemoteDataSourceImpl(apiService ,apiKey)
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
    fun provideMoviesRepository(trendingMoviesRemoteDataSource: TrendingMoviesRemoteDataSource, trendingMoviesLocalDataSource: TrendingMoviesLocalDataSource, cacheTrendingMovie: CacheTrendingMovie):MoviesRepository
    {
        return MoviesRepositoryImpl(trendingMoviesRemoteDataSource, trendingMoviesLocalDataSource, cacheTrendingMovie)
    }
}