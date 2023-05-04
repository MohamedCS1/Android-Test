package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.MoviesDao
import com.example.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context:Application):MoviesDatabase
    {
        return Room.databaseBuilder(context ,MoviesDatabase::class.java ,"moviesDatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(moviesDatabase:MoviesDatabase):MoviesDao
    {
        return moviesDatabase.moviesDao()
    }
}