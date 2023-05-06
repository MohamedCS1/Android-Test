package com.example.androidtest.presentation.ui.main

import android.view.View
import com.example.androidtest.util.DataSource
import com.example.domain.models.trendingMovies.Movie

interface OnMovieClickListener {
    fun onMovieClick(movie:Movie ,dataSource: DataSource)
}