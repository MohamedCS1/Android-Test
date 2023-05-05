package com.example.androidtest.presentation.ui.main

import android.view.View
import com.example.domain.models.trendingMovies.Movie

interface OnMovieClickListiner {
    fun onMovieClick(itemView:View ,movie:Movie)
}