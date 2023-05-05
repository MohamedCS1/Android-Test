package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.moviesDetail.MovieDetailDao
import com.example.data.local.movies.MoviesDao
import com.example.data.local.movies.MoviesDatabase
import com.example.data.local.moviesDetail.MoviesDetailDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesDatabaseModule {
    @Provides
    @Singleton
    fun provideMoviesDatabase(context:Application): MoviesDatabase
    {
        return Room.databaseBuilder(context , MoviesDatabase::class.java ,"moviesDatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao
    {
        return moviesDatabase.moviesDao()
    }

    @Provides
    @Singleton
    fun provideMoviesDetailDatabase(context:Application): MoviesDetailDatabase
    {
        return Room.databaseBuilder(context , MoviesDetailDatabase::class.java ,"moviesDetailDatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDetailDao(moviesDetailDatabase: MoviesDetailDatabase): MovieDetailDao
    {
        return moviesDetailDatabase.moviesDetailDao()
    }
}