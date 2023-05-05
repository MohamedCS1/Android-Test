package com.example.androidtest.di

import com.example.domain.repository.MoviesRepository
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRemoteTrendingMoviesUseCase(moviesRepository: MoviesRepository):GetRemoteTrendingMovies
    {
        return GetRemoteTrendingMovies(moviesRepository)
    }

    @Provides
    fun provideGetLocalTrendingMoviesUseCase(moviesRepository: MoviesRepository):GetLocalTrendingMovies
    {
        return GetLocalTrendingMovies(moviesRepository)
    }

    @Provides
    fun provideGetRemoteDetailTrendingMovie(moviesRepository: MoviesRepository):GetRemoteDetailTrendingMovie
    {
        return GetRemoteDetailTrendingMovie(moviesRepository)
    }

    @Provides
    fun provideGetLocalDetailTrendingMovie(moviesRepository: MoviesRepository):GetLocalDetailTrendingMovie
    {
        return GetLocalDetailTrendingMovie(moviesRepository)
    }

    @Provides
    fun provideCacheLocalDetailTrendingMovie(moviesRepository: MoviesRepository):CacheDetailTrendingMovie
    {
        return CacheDetailTrendingMovie(moviesRepository)
    }

    @Provides
    fun provideCacheMovieUseCase(moviesRepository: MoviesRepository): CacheTrendingMovie
    {
        return CacheTrendingMovie(moviesRepository)
    }

}