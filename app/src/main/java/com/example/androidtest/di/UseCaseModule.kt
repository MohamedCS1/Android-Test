package com.example.androidtest.di

import com.example.domain.repository.MoviesRepository
import com.example.domain.usecase.CacheMovie
import com.example.domain.usecase.GetLocalTrendingMovies
import com.example.domain.usecase.GetRemoteTrendingMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    fun provideCacheMovieUseCase(moviesRepository: MoviesRepository): CacheMovie
    {
        return CacheMovie(moviesRepository)
    }

}