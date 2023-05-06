package com.example.androidtest.presentation.ui.main

import com.example.domain.models.trendingMovies.Movie

interface OnMovieClickListener {
    fun onMovieClick(movie:Movie)
}